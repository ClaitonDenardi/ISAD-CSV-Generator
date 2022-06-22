/*
 * Claiton Denardi Paulus
 * Gerador de CSV padrão ISAD(Simplificado)
 */
package geradorcsv.isad;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author claiton
 */
public class GeradorCSVISAD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
       System.out.println("Gerador de CSV padrão ISAD(Simplificado) ");
       System.out.println("Digite o diretório de destino do CSV: ");
       Scanner scanner = new Scanner(System.in);  
       String destino = scanner.nextLine(); 
       System.out.println("Digite o caminho do arquivo e pressione ENTER: ");
       String caminho = scanner.nextLine(); 
       System.out.println("");
       System.out.println("Seu arquivo CSV será gerado com os seguintes metadados ");
       File f = new File(caminho);
       System.out.println("tilte: " +f.getName());
       System.out.println("digitalObjectURI: " +caminho);
       System.out.println("eventStartDates: " +new GeradorCSVISAD().getMetadata(new File(caminho)));
 
        try (PrintWriter pw = new PrintWriter(new File(destino+"metadata.csv"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("title");
            sb.append(',');
            sb.append("digitalObjectURI");
            sb.append(',');
            sb.append("eventStartDates");
            sb.append('\n');
            
            sb.append(f.getName());
            sb.append(',');
            sb.append(caminho);
            sb.append(',');
            sb.append(new GeradorCSVISAD().getMetadata(new File(caminho)));
            sb.append('\n');
            
            pw.write(sb.toString());
        }
        System.out.println("CSV gerado com sucesso!");
        System.out.println("");
        System.out.println("O arquivo gerado chama-se 'metadata.csv' e pode ser ");
        System.out.println("encontrado em: "+ destino +"metadata.csv");
        
    }
    public String getMetadata(File file)
    {       
       try{         
        Path p = Paths.get(file.getAbsolutePath());
        BasicFileAttributes view
           = Files.getFileAttributeView(p, BasicFileAttributeView.class)
                  .readAttributes();
        FileTime fileTime=view.creationTime();
        return (""+new SimpleDateFormat("dd/MM/yyyy").format((fileTime.toMillis())));
       }
       catch(IOException e){ 
        e.printStackTrace(); 
       }
       return ""; 
   }
}
