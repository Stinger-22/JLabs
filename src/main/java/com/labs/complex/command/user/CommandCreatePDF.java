package com.labs.complex.command.user;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.labs.complex.account.IAccount;
import com.labs.complex.account.User;
import com.labs.complex.being.Action;
import com.labs.complex.being.Person;
import com.labs.complex.being.Tax;
import com.labs.complex.command.Command;
import com.labs.complex.db.DBConnection;
import com.labs.complex.exception.AccessDeniedException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class CommandCreatePDF implements Command {
    private User account;

    public CommandCreatePDF(IAccount account) throws AccessDeniedException {
        if (!(account instanceof User)) {
            throw new AccessDeniedException(account);
        }
        this.account = (User) account;
    }

    @Override
    public void execute() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("taxes.pdf"));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            Paragraph top = new Paragraph();
            top.setAlignment(Element.ALIGN_CENTER);
            top.add(new Chunk("Report on taxation of the individual entrepreneur"));
            top.add("\n");
            top.add(new LineSeparator());
            top.add("\n");
            document.add(top);

            Paragraph creationDate = new Paragraph();
            creationDate.setAlignment(Element.ALIGN_RIGHT);
            LocalDateTime date = LocalDateTime.now();
            creationDate.add("Date: " + date.format(DateTimeFormatter.ISO_DATE) + "\n\n");
            document.add(creationDate);

            Paragraph middle = new Paragraph();
            Person person = account.getPerson();
            middle.add("Name: " + person.getName() + "\nSurname: " + person.getSurname() + "\nSalary: " + person.getSalary() + "\n");
            PdfPTable table = new PdfPTable(2);
            setupTable(table);
            addTax(table);
            middle.add("\n");
            middle.add(table);
            middle.add("\n");
            CommandCalculateTotalTax command = new CommandCalculateTotalTax(account);
            command.execute();
            middle.add("Total tax: " + Math.floor(command.getValue() * 100) / 100);
            document.add(middle);
            document.add(new Chunk("\n\n\n\n\n"));

            Paragraph bottom = new Paragraph();
            bottom.add("Signature:\n");
            bottom.add(new LineSeparator());
            document.add(bottom);

            document.close();
        }
        catch (IOException | DocumentException | AccessDeniedException exception) {
            exception.printStackTrace();
        }
    }

    private void setupTable(PdfPTable table) {
        setupTableHeader(table);
    }

    private void setupTableHeader(PdfPTable table) {
        Stream.of("Object of taxation", "Tax value to pay").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setPhrase(new Phrase(columnTitle));
            header.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(header);
        });
    }

    private void addTax(PdfPTable table) {
        for (Tax tax : account.getTaxList()) {
            table.addCell(tax.getDescription());
            table.addCell(Double.toString(tax.calculate(account)));
        }
        String dateDiff = "SELECT DATEDIFF(month, GETDATE(), ?)";
        PreparedStatement statement = DBConnection.getInstance().prepareStatement(dateDiff);
        ResultSet resultSet;
        try {
            for (Action action : account.getActionList()) {
                statement.setDate(1, action.getDate());
                resultSet = statement.executeQuery();
                resultSet.next();
                if (resultSet.getInt(1) == 0) {
                    table.addCell(action.getName() + ": " + action.getValue());
                    table.addCell(Double.toString(Math.floor(action.calculate(account) * 100) / 100));
                }
            }
            statement.close();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
