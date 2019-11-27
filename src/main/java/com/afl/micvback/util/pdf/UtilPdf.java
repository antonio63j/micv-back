package com.afl.micvback.util.pdf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.lang.String;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.constants.StandardFonts;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.afl.micvback.models.entity.ConocimientoTecnico;
import com.afl.micvback.models.entity.Curso;
import com.afl.micvback.models.entity.Experiencia;
import com.afl.micvback.models.services.IConocimientoTecnicoService;
import com.afl.micvback.models.services.ICursoService;
import com.afl.micvback.models.services.IExperienciaService;

@Service

public class UtilPdf {
	@Autowired
	private ICursoService cursoService;
	
	@Autowired
	private IConocimientoTecnicoService conocimientoTecnicoService;
	
	@Autowired
	private IExperienciaService experienciaService;
		
	PdfFont fontApartadoDescripcion;
	PdfFont fontApartado;
	PdfFont fontCampoDescripcion;
	PdfFont fontCampo;
	PdfFont fontCabeceraCelda;
	PdfFont fontCelda;
	
	
    Style styleApartadoDescripcion;
	
	Float posCentral;

	PdfDocument pdfDoc;
	Document doc;

	public UtilPdf() throws Exception {
//		pdfDoc = new PdfDocument(new PdfWriter(dest));
//		PageSize pageSize = PageSize.A4;
//		doc = new Document(pdfDoc, pageSize);
		setEstilos();
		
	}

	private void setEstilos() throws IOException {
		
		


		

	}

	private void setPosiciones(PageSize pageSize) {
		posCentral = pageSize.getWidth() - doc.getLeftMargin() - doc.getRightMargin();
	}

	private void addTitulo() throws IOException {
		PdfFont fontTitulo = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
		
		Style styleTitulo = new Style().setFont(fontTitulo).setFontSize(16).setFontColor(ColorConstants.BLACK)
				.setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.5f);
		
		Paragraph tit = new Paragraph().addTabStops(new TabStop(posCentral / 2, TabAlignment.CENTER)).add(new Tab())
				.add(new Text("Curriculum Vitae")).addStyle(styleTitulo);
		
		
		doc.add(tit);

	}
	
	private void addApartado(String texto) throws IOException {
		
		PdfFont fontApartadoDescripcion = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
		Style styleApartadoDescripcion = new Style().setFont(fontApartadoDescripcion).setFontSize(12).setFontColor(ColorConstants.LIGHT_GRAY)
				.setBackgroundColor(ColorConstants.BLACK, 0.9f);
				
		Text text = new Text(texto).addStyle(styleApartadoDescripcion);
		doc.add(new Paragraph(text).setMargin(0));
		
	}

	private void addSubApartado(String texto) throws IOException {
		
		PdfFont fontApartadoDescripcion = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
		Style styleApartadoDescripcion = new Style().setFont(fontApartadoDescripcion).setFontSize(11).setFontColor(ColorConstants.BLACK)
				.setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.9f);
				
		Text text = new Text(texto).addStyle(styleApartadoDescripcion);
		doc.add(new Paragraph(text).setMargin(0));
		
	}
	
	private void addDatosPersonales() throws IOException {
		addApartado("Datos Personales");
		
		PdfFont fontApartado = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
		PdfFont fontCampoDescripcion = PdfFontFactory.createFont(StandardFonts.HELVETICA);
		PdfFont fontCampo = PdfFontFactory.createFont(StandardFonts.HELVETICA);

		
		// outer table
		Table outertable = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();

		// inner table 1
		Table innertable = new Table(UnitValue.createPercentArray(new float[] { 4, 12, 1, 4, 12 }))
				.useAllAvailableWidth();

		// first row
		// column 1
		Cell cell = new Cell().add(new Paragraph("Nombre:").setFont(fontCampoDescripcion).setFontSize(8));
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		// column 2
		cell = new Cell().add(new Paragraph("Antonio Fernandez Lucena").setFont(fontCampo).setFontSize(8)
				.setFontColor(ColorConstants.DARK_GRAY, 0.9f));
		cell.setPaddingLeft(2);
		innertable.addCell(cell);

		// spacing 3
		cell = new Cell();
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		// column 4
		// spacing
		cell = new Cell();
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);
		// spacing
		cell = new Cell();
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		// spacing
		cell = new Cell(1, 5);
		cell.setHeight(3);
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		// second row
		// column 1
		cell = new Cell().add(new Paragraph("Nacionalidad:").setFont(fontCampoDescripcion).setFontSize(8));
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		// column 2
		cell = new Cell().add(new Paragraph("Española").setFont(fontCampo).setFontSize(8)
				.setFontColor(ColorConstants.DARK_GRAY, 0.9f));
		cell.setPaddingLeft(2);
		innertable.addCell(cell);

		// spacing
		cell = new Cell();
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		cell = new Cell().add(new Paragraph("Residencia:").setFont(fontCampoDescripcion).setFontSize(8));
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);


		cell = new Cell().add(new Paragraph("Madrid").setFont(fontCampo).setFontSize(8)
				.setFontColor(ColorConstants.DARK_GRAY, 0.9f));
		cell.setPaddingLeft(2);
		innertable.addCell(cell);

		// spacing
		cell = new Cell(1, 5);
		cell.setHeight(3);
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);
		
		// row 3
		// column 1
		cell = new Cell().add(new Paragraph("Teléfono:").setFont(fontCampoDescripcion).setFontSize(8));
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		// column 2
		cell = new Cell().add(new Paragraph("627336511").setFont(fontCampo).setFontSize(8)
				.setFontColor(ColorConstants.DARK_GRAY, 0.9f));
		cell.setPaddingLeft(2);
		innertable.addCell(cell);

		// spacing
		cell = new Cell();
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		cell = new Cell().add(new Paragraph("Email:").setFont(fontCampoDescripcion).setFontSize(8));
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);


		cell = new Cell().add(new Paragraph("antonio63j@hotmail.com").setFont(fontCampo).setFontSize(8)
				.setFontColor(ColorConstants.DARK_GRAY, 0.9f));
		cell.setPaddingLeft(2);
		innertable.addCell(cell);

		// spacing
		cell = new Cell(1, 5);
		cell.setHeight(3);
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);
		// row 4
		
		// column 1
		cell = new Cell().add(new Paragraph("CV actualizado:").setFont(fontCampoDescripcion).setFontSize(8));
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		// column 2
		cell = new Cell().add(new Paragraph("http://antonioFernandezLucena/micv").setFont(fontCampo).setFontSize(8)
				.setFontColor(ColorConstants.DARK_GRAY, 0.9f));
		cell.setPaddingLeft(2);
		innertable.addCell(cell);

		// spacing 3
		cell = new Cell();
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		// column 4
		// spacing
		cell = new Cell();
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);
		// spacing
		cell = new Cell();
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		// spacing
		cell = new Cell(1, 5);
		cell.setHeight(3);
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);
		
		// row 5
		// column 1
		cell = new Cell().add(new Paragraph("Titulación:").setFont(fontCampoDescripcion).setFontSize(8));
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		// column 2
		cell = new Cell().add(new Paragraph("Ingeniero/Diplomado informática").setFont(fontCampo).setFontSize(8)
				.setFontColor(ColorConstants.DARK_GRAY, 0.9f));
		cell.setPaddingLeft(2);
		innertable.addCell(cell);

		// spacing
		cell = new Cell();
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		cell = new Cell().add(new Paragraph("Año:").setFont(fontCampoDescripcion).setFontSize(8));
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);


		cell = new Cell().add(new Paragraph("1999").setFont(fontCampo).setFontSize(8)
				.setFontColor(ColorConstants.DARK_GRAY, 0.9f));
		cell.setPaddingLeft(2);
		innertable.addCell(cell);

		// spacing
		cell = new Cell(1, 5);
		cell.setHeight(3);
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);
		
		// row 6
		// column 1
		cell = new Cell().add(new Paragraph("Universidad:").setFont(fontCampoDescripcion).setFontSize(8));
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		// column 2
		cell = new Cell().add(new Paragraph("Universidad politécnica de Madrid").setFont(fontCampo).setFontSize(8)
				.setFontColor(ColorConstants.DARK_GRAY, 0.9f));
		cell.setPaddingLeft(2);
		innertable.addCell(cell);

		// spacing 3
		cell = new Cell();
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		// column 4
		// spacing
		cell = new Cell();
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);
		// spacing
		cell = new Cell();
		cell.setBorder(Border.NO_BORDER);
		innertable.addCell(cell);

		// spacing
		cell = new Cell(1, 5);
		cell.setHeight(3);
		cell.setBorder(Border.NO_BORDER);
		// row 7
		
		// first nested table
		cell = new Cell().add(innertable);
		cell.setNextRenderer(new RoundedBorderCellRenderer(cell));
		cell.setBorder(Border.NO_BORDER);
		cell.setPadding(8);
		outertable.addCell(cell);
		
		// add the table
		doc.add(outertable);
	}

	private void addCursos() throws IOException {
		
		PdfFont fontCabeceraCelda = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
		Style styleCabeceraCelda = new Style().setFont(fontCabeceraCelda).setFontSize(10).setFontColor(ColorConstants.BLACK)
				.setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.9f);
		
		PdfFont fontCelda = PdfFontFactory.createFont(StandardFonts.HELVETICA);
		Style styleCelda = new Style().setFont(fontCelda).setFontSize(10).setFontColor(ColorConstants.DARK_GRAY);
		
		addApartado("Cursos realizados");

		List<Curso> misCursos = cursoService.findAll();
       
        // Create table with 3 columns of similar length
        Table table = new Table(new float[]{4, 4, 4, 4});
        table.setWidth(UnitValue.createPercentValue(100));
        
        // adding header
 
        table.addHeaderCell(new Cell().add(new Paragraph("Nombre").addStyle(styleCabeceraCelda)));
        table.addHeaderCell(new Cell().add(new Paragraph("Inicio").addStyle(styleCabeceraCelda)));
        table.addHeaderCell(new Cell().add(new Paragraph("Fin").addStyle(styleCabeceraCelda)));
        table.addHeaderCell(new Cell().add(new Paragraph("Horas").addStyle(styleCabeceraCelda)));

        // adding rows
        for(Curso curso : misCursos) {
            table.addCell(new Cell().add(new Paragraph(curso.getNombre()).addStyle(styleCelda)));
            table.addCell(new Cell().add(new Paragraph(curso.getInicio().toString()).addStyle(styleCelda)));
            table.addCell(new Cell().add(new Paragraph(curso.getInicio().toString()).addStyle(styleCelda)));
            table.addCell(new Cell().add(new Paragraph(curso.getHoras().toString()).addStyle(styleCelda)));
            
        }
        
        Table outertable = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
        Cell cell = new Cell().add(table);
		cell.setNextRenderer(new RoundedBorderCellRenderer(cell));
		cell.setBorder(Border.NO_BORDER);
		cell.setPadding(8);
		outertable.addCell(cell);
        
        doc.add(outertable);
		
	}

	private void addConocimientos() throws IOException{
		PdfFont fontCabeceraCelda = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
		Style styleCabeceraCelda = new Style().setFont(fontCabeceraCelda).setFontSize(10).setFontColor(ColorConstants.BLACK)
				.setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.9f);
		
		PdfFont fontCelda = PdfFontFactory.createFont(StandardFonts.HELVETICA);
		Style styleCelda = new Style().setFont(fontCelda).setFontSize(10).setFontColor(ColorConstants.DARK_GRAY);
		
		addApartado("Conocimientos técnicos");

		List<ConocimientoTecnico> conocimientos = conocimientoTecnicoService.findAll();
       
        // Create table with 3 columns of similar length
        Table table = new Table(new float[]{4, 4, 4, 4});
        table.setWidth(UnitValue.createPercentValue(100));
       
        // adding header
 
        table.addHeaderCell(new Cell().add(new Paragraph("Tema").addStyle(styleCabeceraCelda)));
        table.addHeaderCell(new Cell().add(new Paragraph("Tipo").addStyle(styleCabeceraCelda)));
        table.addHeaderCell(new Cell().add(new Paragraph("Nivel").addStyle(styleCabeceraCelda)));
        table.addHeaderCell(new Cell().add(new Paragraph("Comentario").addStyle(styleCabeceraCelda)));

        // adding rows
        for(ConocimientoTecnico cono : conocimientos) {
            table.addCell(new Cell().add(new Paragraph(cono.getNombre()).addStyle(styleCelda)));
            table.addCell(new Cell().add(new Paragraph(cono.getTipo().toString()).addStyle(styleCelda)));
            table.addCell(new Cell().add(new Paragraph(cono.getNivel().toString()).addStyle(styleCelda)));
            table.addCell(new Cell().add(new Paragraph(cono.getComentario().toString()).addStyle(styleCelda)));         
        }
        
        Table outertable = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
        Cell cell = new Cell().add(table);
		cell.setNextRenderer(new RoundedBorderCellRenderer(cell));
		cell.setBorder(Border.NO_BORDER);
		cell.setPadding(8);
		outertable.addCell(cell);
        
        doc.add(outertable);

	}

	private Table addTableExperienciaCabecera (Style styleCabeceraCelda) {
		
        Table table = new Table(new float[]{4, 4, 4, 4, 4});
        table.setWidth(UnitValue.createPercentValue(100));
        
    	table.addHeaderCell(new Cell().add(new Paragraph("Empresa").addStyle(styleCabeceraCelda)));
    	table.addHeaderCell(new Cell().add(new Paragraph("Cliente").addStyle(styleCabeceraCelda)));
        table.addHeaderCell(new Cell().add(new Paragraph("Inicio").addStyle(styleCabeceraCelda)));
        table.addHeaderCell(new Cell().add(new Paragraph("Fin").addStyle(styleCabeceraCelda)));
        table.addHeaderCell(new Cell().add(new Paragraph("Sector").addStyle(styleCabeceraCelda)));
        return table;
	}
	
	private Table addTableExperiencia (Experiencia experiencia, Style styleCabeceraCelda, Style styleCelda) {
		
        Table table = new Table(new float[]{4, 4, 4, 4, 4});
        table.setWidth(UnitValue.createPercentValue(100));
        
    	table.addHeaderCell(new Cell().add(new Paragraph("Empresa").addStyle(styleCabeceraCelda)));
    	table.addHeaderCell(new Cell().add(new Paragraph("Cliente").addStyle(styleCabeceraCelda)));
        table.addHeaderCell(new Cell().add(new Paragraph("Inicio").addStyle(styleCabeceraCelda)));
        table.addHeaderCell(new Cell().add(new Paragraph("Fin").addStyle(styleCabeceraCelda)));
        table.addHeaderCell(new Cell().add(new Paragraph("Sector").addStyle(styleCabeceraCelda)));
        
        table.addCell(new Cell().add(new Paragraph(experiencia.getEmpresa()).addStyle(styleCelda)));
        table.addCell(new Cell().add(new Paragraph(experiencia.getCliente()).addStyle(styleCelda)));
        table.addCell(new Cell().add(new Paragraph(experiencia.getInicio().toString()).addStyle(styleCelda)));
        table.addCell(new Cell().add(new Paragraph(experiencia.getFin().toString()).addStyle(styleCelda)));
        table.addCell(new Cell().add(new Paragraph(experiencia.getSectorCliente()).addStyle(styleCelda)));
        
        return table;
	}
	
	private void addExperiencia() throws IOException {
		PdfFont fontCabeceraCelda = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
		Style styleCabeceraCelda = new Style().setFont(fontCabeceraCelda).setFontSize(10).setFontColor(ColorConstants.BLACK)
				.setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.9f);
		
		PdfFont fontCelda = PdfFontFactory.createFont(StandardFonts.HELVETICA);
		Style styleCelda = new Style().setFont(fontCelda).setFontSize(10).setFontColor(ColorConstants.DARK_GRAY);
		
		addApartado("Experiencia laboral");
		
		List<Experiencia> experiencias = experienciaService.findAll();
   
		List<IElement> elements;
        for(Experiencia experiencia : experiencias) {
            
        	//doc.add(addTableExperienciaCabecera(styleCabeceraCelda));
        	doc.add(addTableExperiencia(experiencia, styleCabeceraCelda, styleCelda));
    		doc.add(new Paragraph());
	      	addSubApartado(" Actividad ");
    		//String htmlSource = experiencia.getActividades();
    		elements = HtmlConverter.convertToElements(experiencia.getActividades());
           
    		for (IElement element : elements) {
                if (element instanceof IBlockElement)
                    doc.add((IBlockElement) element);
                else
                {
                    System.out.println("Error en parse htmt to pdf");
                }
            }
    		doc.add(new Paragraph());
	      	addSubApartado(" Experiencia ");
    		elements = HtmlConverter.convertToElements(experiencia.getExperiencia());
           
    		for (IElement element : elements) {
                if (element instanceof IBlockElement)
                    doc.add((IBlockElement) element);
                else
                {
                    System.out.println("Error en parse htmt to pdf");
                }
            }

            
        }
	}

	public void generarPdfCv(String dest) throws FileNotFoundException, IOException {
		pdfDoc = new PdfDocument(new PdfWriter(dest));
		PageSize pageSize = PageSize.A4;
		doc = new Document(pdfDoc, pageSize, true);
		
		setPosiciones(pageSize);
		
		addTitulo();
		doc.add(new Paragraph());
		doc.add(new Paragraph());

		addDatosPersonales();
		doc.add(new Paragraph());
		doc.add(new Paragraph());

		addConocimientos();
		doc.add(new Paragraph());
		doc.add(new Paragraph());
		
		addExperiencia();
		doc.add(new Paragraph());
		doc.add(new Paragraph());	
		
		addCursos();
		doc.add(new Paragraph());
		doc.add(new Paragraph());		
		
		doc.close();
//		pdfDoc.close();
	}

	private static class RoundedBorderCellRenderer extends CellRenderer {
		public RoundedBorderCellRenderer(Cell modelElement) {
			super(modelElement);
		}

		// If renderer overflows on the next area, iText uses getNextRender() method to
		// create a renderer for the overflow part.
		// If getNextRenderer isn't overriden, the default method will be used and thus
		// a default rather than custom
		// renderer will be created
		@Override
		public IRenderer getNextRenderer() {
			return new RoundedBorderCellRenderer((Cell) modelElement);
		}

		@Override
		public void draw(DrawContext drawContext) {
			drawContext.getCanvas().roundRectangle(getOccupiedAreaBBox().getX() + 1.5f,
					getOccupiedAreaBBox().getY() + 1.5f, getOccupiedAreaBBox().getWidth() - 3,
					getOccupiedAreaBBox().getHeight() - 3, 4);
			drawContext.getCanvas().stroke();
			super.draw(drawContext);
		}
	}

}
