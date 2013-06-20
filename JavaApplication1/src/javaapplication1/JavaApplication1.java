/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author Cristorb
 */
import java.awt.Rectangle;
import java.awt.print.PageFormat;
import java.io.*;
import java.util.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripperByArea;

/**
 *
 * @author www.ubicuos.com
 */
public class JavaApplication1 {

    private String sCadena;

    public static void main(String args[]) throws FileNotFoundException, IOException {
        JavaApplication1 pdf = new JavaApplication1();
        pdf.JavaApplication1();
    }

    public static void extraerDatos(String cadena) throws IOException {
//        String cadena = "una cadena de algunas palabras";
        int i = 0;
        StringTokenizer token = new StringTokenizer(cadena);
        do {
//            System.out.println(token.nextToken());
            String a = token.nextToken();
            String nombre = null;

            escribirTXT(a);

//            if (a.equals(":")) { //comienzo primera linea de info de personas
//                i++;
//                if (i == 2) {
//                    texto = token.nextToken();
//                    nombre = texto;
////                    System.out.print("Nombre: " + texto + " ");
////                    escribirTXT(texto);
//                    i = 0;
//                    // guardar todo el nombre hasta que aparesca el RUT
//                    while (a.contains("-") == false && a.contains(".") == false) {
//                        a = token.nextToken();
//                        if (a.contains("-") == false && a.contains(".") == false) {
//                            nombre = nombre + " " + a;
//                        }
//                    }
//                    System.out.print("Nombre: " + nombre + " ");
//                    nombre=null;
//                }
//
//            }

            if (a.equals(":")) {
//                i++;
//                if (i == 2) {
                a = token.nextToken();
                i = 0;
                nombre = a;
//                    System.out.println("NOMBREEEEEEEEEEEE: " + nombre);
                // guardar todo el nombre hasta que aparesca el RUT
                while (a.contains("-") == false && a.contains(".") == false) {
                    a = token.nextToken();
                    if (a.contains("-") == false && a.contains(".") == false) {
                        nombre = nombre + " " + a;
                    }
                }
                System.out.print("Nombre: " + nombre + " ");
                nombre = null;
//                }
            }


            if (a.equals("VAR") || a.equals("MUJ")) {
//                String sexo = token.nextToken();
                System.out.println("sexo: " + a + " ");

                if (token.hasMoreTokens()) {
                    nombre = token.nextToken();
                    while (a.contains("-") == false && a.contains(".") == false) {
                        a = token.nextToken();
                        if (a.contains("-") == false && a.contains(".") == false) {
                            nombre = nombre + " " + a;
                        }
                    }
                    System.out.print("Nombre: " + nombre + " ");
                    nombre = null;
//                    escribirTXT(nombre);
                }
            }


            if (a.contains("-") && a.contains(".")) { //capturar el RUT

                System.out.print("RUT: " + a + " ");
            }


//            if (a.equals("V") || a.equals("M")) { // termina la linea de una persona
//            if (a.equals("\n")) { // termina la linea de una persona
//                if (token.hasMoreTokens()) {
//                    texto = token.nextToken();
//                    System.out.print("texto: " + texto + " ");
//                    escribirTXT(texto);
//                }
//            }




        } while (token.hasMoreTokens());
    }

    public static void extraerDomicilio(String cadena) throws IOException {
//        StringTokenizer token = new StringTokenizer(cadena);
//        do {
//            System.out.println(token.nextToken());
//
//        } while (token.hasMoreTokens());
        System.out.println(cadena);
    }

    public static void extraerCir(String cadena) throws IOException {
        System.out.println(cadena);
    }

    public static void extraerMesa(String cadena) throws IOException {
        System.out.println(cadena);
    }

    public static void escribirTXT(String texto) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        File file = new File("E:/Universidad/SERVEL/Salida.txt");

        if (!file.exists()) {

            if (file.createNewFile()) {
                System.out.println("El fichero se ha creado correctamente");
            } else {

                System.out.println("No ha podido ser creado el fichero");
            }
        }
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF8"))) {
            out.write(texto);
            //        out.write("\n");
            out.newLine();
        }
    }

    @SuppressWarnings("empty-statement")
    public void JavaApplication1() throws FileNotFoundException, IOException {
        int h = 0;
//        String nombre = "ALVARADO GARATE MURIEL";
        BufferedReader bf = new BufferedReader(new FileReader("E:/Universidad/SERVEL/documentos.txt"));
        while ((sCadena = bf.readLine()) != null) {
            h++;
            System.out.println(h + " - " + sCadena);


            try {
                int i;
                // carga de documentos
                PDDocument pd = PDDocument.load("E:/Universidad/SERVEL/" + sCadena);
//            PDDocument pd = PDDocument.load("E:/Universidad/SERVEL/A1322001.pdf");
                // obtiene las paginas y las almacena en una lista
                List l = pd.getDocumentCatalog().getAllPages();
                Object[] obj = l.toArray();
                //exploramos la lista para obtener las paginas
                for (i = 0; i < 1; i++) {
//////////////////                for (i = 0; i < l.size(); i++) {
                    System.out.println("---------Pagina" + (i + 1) + "-----------------------");
                    PDPage page = (PDPage) obj[i];
                    //Obtenemos el formato de la pagina
                    PageFormat pageFormat = pd.getPageFormat(i);
                    //Obtenemos las dimensiones de la pagina
                    Double d1 = new Double(pageFormat.getHeight());
                    Double d2 = new Double(pageFormat.getWidth());
                    int width = d1.intValue();
                    int eigth = d2.intValue();
                    //Este componente nos ayudara a obtener el texto
                    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                    //Definimos un area en donde buscaremos texto
//                    Rectangle rect = new Rectangle(0, 0, 750, eigth);
                    Rectangle rect = new Rectangle(0, 0, 320, eigth); // nombre, rut , sexo
                    Rectangle rect2 = new Rectangle(320, 60, 236, eigth); // Direccion
                    Rectangle rect3 = new Rectangle(556, 60, 100, eigth); // Cirscuncripcion
//                    Rectangle rect4 = new Rectangle(320, 60, 430, eigth); // Mesa
                    //se registra el area del trabajo
                    stripper.addRegion("area1", rect);
                    stripper.addRegion("area2", rect2);
                    stripper.addRegion("area3", rect3);
//                    stripper.addRegion("area4", rect4);
                    //extraemos el texto del area
                    stripper.extractRegions(page);
                    //Imprimimos el contenido
//                   System.out.println("Texto leido:" + rect); //medidas de donde se saca el texto
//                    System.out.println(stripper.getTextForRegion("area1").toString());
                    String texto1 = stripper.getTextForRegion("area1").toString();
                    String texto2 = stripper.getTextForRegion("area2").toString();
                    String texto3 = stripper.getTextForRegion("area3").toString();
//                    String texto4 = stripper.getTextForRegion("area4").toString();
//                    extraerDatos(texto1);
                    extraerDomicilio(texto2);
//                    extraerCir(texto3);
//                    extraerMesa(texto4);

                }
                pd.close();
            } catch (IOException ioe) {
            }
//        }
        }
    }
}