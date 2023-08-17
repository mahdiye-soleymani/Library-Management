package com.sepehr.librarymanagement.service;

import com.sepehr.librarymanagement.entity.BorrowedBook;
import com.sepehr.librarymanagement.entity.Member;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class MonthlyReportGenerator {

    @Value("${report.location}")
    private String reportLocation;

    private final MemberService memberService;

    public MonthlyReportGenerator(MemberService memberService) {
        this.memberService = memberService;
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void generateMonthlyReport() {
        List<Member> members = memberService.getAllMembers();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Monthly Report");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int rowNum = 0;

        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Member Name");
        headerRow.createCell(1).setCellValue("Book Title");
        headerRow.createCell(2).setCellValue("Borrow Date");
        headerRow.createCell(3).setCellValue("Return Date");
        headerRow.createCell(4).setCellValue("Days Borrowed");

        for (Member member : members) {
            List<BorrowedBook> borrowedBooks = member.getBorrowedBooks();
            for (BorrowedBook borrowedBook : borrowedBooks) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(member.getFirstName() + " " + member.getLastName());
                row.createCell(1).setCellValue(borrowedBook.getBook().getTitle());
                row.createCell(2).setCellValue(borrowedBook.getBorrowDate().format(dateFormatter));
                row.createCell(3).setCellValue(borrowedBook.getReturnDate() != null ? borrowedBook.getReturnDate().format(dateFormatter) : "");
                long daysBorrowed = borrowedBook.getBorrowDate().until(LocalDate.now()).getDays();
                row.createCell(4).setCellValue(daysBorrowed);
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(reportLocation + "monthly_report.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
