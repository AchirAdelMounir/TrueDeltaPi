package util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import Entities.Security;
import Interfaces.SecuritiesServicesInterfaceLocal;

/**
 * Servlet implementation class ExportExcel
 */
@WebServlet( urlPatterns="/export.xls" )
public class ExcelExport extends HttpServlet {

    private static final long serialVersionUID = 8181226250983408872L;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                                    throws ServletException, IOException {
    	
    	SecuritiesServicesInterfaceLocal service = null;
        
        

        try {
          
            
            try ( HSSFWorkbook workbook = new HSSFWorkbook())
                  {
            	List<Security> Ls=service.DisplaySecurities();               
                // --- Define some cell styles ---
                CellStyle headerCellStyle = workbook.createCellStyle();
                HSSFFont font = workbook.createFont();
                font.setBold( true );
                headerCellStyle.setFont(font);  
                headerCellStyle.setAlignment( HorizontalAlignment.CENTER );

                CellStyle numericCellStyle = workbook.createCellStyle();
                numericCellStyle.setDataFormat( 
                    workbook.getCreationHelper().createDataFormat().getFormat("0.00"));

                // --- Export T_Articles table ---
                HSSFSheet articleSheet = workbook.createSheet( "Securities" );
                HSSFRow row = articleSheet.createRow( 0 );
                HSSFCell cell;

                row.setRowStyle( headerCellStyle );
                cell = row.createCell( 0 );
                cell.setCellStyle( headerCellStyle);
                cell.setCellValue( "Open" );
                
                cell = row.createCell( 1 );
                cell.setCellStyle( headerCellStyle);
                cell.setCellValue( "Close" );
                
                cell = row.createCell( 2 );
                cell.setCellStyle( headerCellStyle);
                cell.setCellValue( "High" );
                
                cell = row.createCell( 3 );
                cell.setCellStyle( headerCellStyle);
                cell.setCellValue( "Low" );
                
                int rowIndex = 1;
              
               
                        for ( Security s:Ls ) {
                            row = articleSheet.createRow( rowIndex++ );
                            cell = row.createCell( 0 ); 
                            cell.setCellValue( s.getPrice() );

                            cell = row.createCell( 1 ); 
                            cell.setCellValue( s.getType() );

                            cell = row.createCell( 2 ); 
                            cell.setCellValue( s.getId());

                            

                        
                    
                }
                
                // Auto size each columns of the article's sheet
                for( int i=0; i<4; i++ ) articleSheet.autoSizeColumn( i );
            
                // --- Export T_Users table ---
                HSSFSheet userSheet = workbook.createSheet( "T_Users table" );
                // TODO: Continue to export other data 
            
            
                // Write the XLSX file to the HTTP socket
                response.setContentType( "application/vnd.ms-excel" );
                try ( OutputStream out = response.getOutputStream() ) {
                    workbook.write( out );
                }
                
            }           
            
        } catch (Exception e) {
            e.printStackTrace();
            
            response.setContentType( "text/html" );
            try ( PrintWriter out = response.getWriter() ) {
                out.println( "An error is produced, please view de server logs " + 
                             "for more informations" );
            }
            
        }
        
    }
    
}
