package mailConfig;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;

import profilePage.DataProfileManager;
import properties.PropertiesManager;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import common.OneShopPanel;

public class PdfCreator {

	private static final String NINJA_SOFTWARE = "NINJA SOFTWARE";
	private static String FILE_PATH = "docs/factura_";
	private static final String PDF_END_LABEL = ".pdf";
	private static final String CLIENT_DATA_SEPARATOR = ": ";
	private static final String AMOUNT_COCTAIL_LABEL = "x";

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);
	private static Font emailFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.UNDERLINE, BaseColor.BLUE);

	private static int documentNumber;
	private static String documentFile;

	public static int getDocumentNumber() {
		return documentNumber;
	}

	public static void setDocumnetNumber() {
		documentNumber = Math.abs(new Random().nextInt() * 12421);
	}

	public static String getPath() {
		return documentFile;
	}

	public static void generateDocument(List<OneShopPanel> pursaches,
			double totalPrice) {
		try {
			Document document = new Document();
			PdfCreator.setDocumnetNumber();
			documentFile = FILE_PATH + documentNumber + PDF_END_LABEL;
			PdfWriter.getInstance(document, new FileOutputStream(documentFile));
			document.open();
			addMetaData(document);
			addClientInfo(document);
			addTable(document, pursaches, totalPrice);
			addComentsInfo(document);
			document.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	private static void addComentsInfo(Document document) {
		Paragraph preface = new Paragraph();
		preface.add(PropertiesManager
				.getProperties(PropertiesManager.COPY_RIGHT_LABEL));
		addEmptyLine(preface, 1);
		preface.add(PropertiesManager
				.getProperties(PropertiesManager.PC_DOCUMENT_FINAL_TEXT));
		addEmptyLine(preface, 1);
		preface.add(PropertiesManager
				.getProperties(PropertiesManager.PC_DOCUMENT_CONTACT));
		preface.add(new Paragraph(SendMail.OUR_MAIL, emailFont));
		addEmptyLine(preface, 1);

		try {
			document.add(preface);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void addTable(Document document,
			List<OneShopPanel> pursaches, double totalPrice) {
		PdfPTable table = new PdfPTable(3);

		PdfPCell c1 = new PdfPCell(
				new Phrase(
						PropertiesManager
								.getProperties(PropertiesManager.PC_DOCUMENT_TABLE_AMOUNT_HEAD),
						smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(
				new Phrase(
						PropertiesManager
								.getProperties(PropertiesManager.PC_DOCUMENT_TABLE_COCTAIL_HEAD),
						smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(
				new Phrase((PropertiesManager
						.getProperties(PropertiesManager.PRICE_LABEL)),
						smallBold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		for (OneShopPanel coctail : pursaches) {
			table.addCell(new Phrase(getAmountCoctail(coctail)
					+ AMOUNT_COCTAIL_LABEL));
			table.addCell(new Phrase(coctail.getName()));
			table.addCell(new Phrase(
					String.valueOf(coctail.getTotalPrice())
							+ PropertiesManager
									.getProperties(PropertiesManager.PRICE_SYMBOL_LABEL)));
		}

		
		table.addCell("");
		table.addCell(new Phrase(PropertiesManager
				.getProperties(PropertiesManager.PC_DOCUMENT_TOTAL_PRICE_TEXT),
				smallBold));
		table.addCell(new Phrase(totalPrice
				+ PropertiesManager
						.getProperties(PropertiesManager.PRICE_SYMBOL_LABEL)));
		Paragraph preface = new Paragraph();
		preface.add(new Paragraph(new Phrase(PropertiesManager
				.getProperties(PropertiesManager.PC_DOCUMENT_TABLE_TITLE),
				subFont)));
		addEmptyLine(preface, 2);
		preface.add(table);
		preface.add(new Paragraph(PropertiesManager
				.getProperties(PropertiesManager.PC_DOCUMENT_IVA_TEXT)));
		addEmptyLine(preface, 3);
		try {
			document.add(preface);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// iText allows to add metadata to the PDF which can be viewed in your Adobe
	// Reader
	// under File -> Properties
	private static void addMetaData(Document document) {
		document.addTitle(PropertiesManager
				.getProperties(PropertiesManager.PC_DOCUMENT_TITLE));
		document.addSubject((PropertiesManager
				.getProperties(PropertiesManager.PC_DOCUMENT_SUBJECT))
				+ documentNumber);
		document.addAuthor(NINJA_SOFTWARE);
		document.addCreator(NINJA_SOFTWARE);
	}

	private static void addClientInfo(Document document)
			throws DocumentException {
		try {
			Image image1 = Image.getInstance(PropertiesManager
					.getProperties(PropertiesManager.IMG_COMPANY_PATH));

			Paragraph preface = new Paragraph();

			addEmptyLine(preface, 1);
			preface.add(image1);
			addEmptyLine(preface, 1);

			preface.add(new Paragraph(PropertiesManager
					.getProperties(PropertiesManager.PC_DOCUMENT_TITLE),
					catFont));

			addEmptyLine(preface, 1);
			
			preface.add(new Paragraph(DataProfileManager.getSystemDate()));
			preface.add(new Paragraph((PropertiesManager
					.getProperties(PropertiesManager.PC_DOCUMENT_SUBJECT))
					+ documentNumber));
			
			addEmptyLine(preface, 1);

			preface.add(new Paragraph(PropertiesManager
					.getProperties(PropertiesManager.PC_CLIENT_DATA_TEXT),
					subFont));

			addEmptyLine(preface, 1);

			addClientData(preface);

			document.add(preface);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void addClientData(Paragraph preface) {
		String[] myData = DataProfileManager.getData();
		for (int i = 0; i < myData.length; i++) {
			preface.add(new Paragraph(DataProfileManager.dataLabels[i]
					+ CLIENT_DATA_SEPARATOR + myData[i]));
		}
		addEmptyLine(preface, 1);

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private static double getAmountCoctail(OneShopPanel coctail) {
		return coctail.getTotalPrice() / coctail.getSinglePrice();
	}

}
