/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tess;

/**
 *
 * @author ba393303
 */
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.*;

public class Tess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

//            System.setProperty("jna.library.path","e:\\jnalib\\win32-x86-64");
//            System.out.println(System.getProperty("jna.library.path"));
            FileWriter arq = null;
            //////
//            args=new String[3];
//            args[0]="e:/95122863A16F40.pdf";
//            args[1]="e:/95122863A16F40.txt";
//            args[2]="e:/95122863A16F40.ter";
            //////
            arq = new FileWriter(args[1]);

            PrintWriter gravarArq = new PrintWriter(arq);

            File imageFile = new File(args[0]);
            //Tika tika = new Tika();
            //tika.setMaxStringLength(-1);
            //String detect =tika.detect(imageFile);

            //String textoExtraido = tika.parseToString(imageFile);
            ITesseract instance = new Tesseract();
            instance.setLanguage("por");
            instance.setDatapath("e:\\tess");

            try {
                String result = instance.doOCR(imageFile);
                result = result.replaceAll("\n", "\r\n");
                gravarArq.println(result);
                gravarArq.close();

                
                FileWriter arqTermino = new FileWriter(args[2]);
                arqTermino.close();
                System.exit(0);

            } catch (TesseractException e) {
                System.err.println(e.getMessage());
            }
        } catch (Exception ex) {
            Logger.getLogger(Tess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
