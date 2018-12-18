package fr.laerce.cinema.service;

import fr.laerce.cinema.model.Film;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class MonImageManager {
    public String ajouteImage(String lettre,String dossier,MultipartFile file){
        String fileName = "";
        try(DirectoryStream<Path> dir = Files.newDirectoryStream(Paths.get("src/main/stockage/images/"+dossier+"/" ),lettre+"*")){

            for (Path filel: dir) {
                if(fileName.compareTo(filel.getFileName().toString())<0){
                    fileName = filel.getFileName().toString();
                }
            }
            fileName = fileName.substring(1);
            Integer tempo = Integer.parseInt(fileName.substring(0,fileName.lastIndexOf('.')))+1;
            String resultat =String.valueOf(tempo);
            for(int i = String.valueOf(tempo).length(); i<4;i++){
                resultat = 0+resultat;
            }
            fileName = lettre+resultat+".jpg";

            byte[] bytes = file.getBytes();
            Path path = Paths.get("src/main/stockage/images/"+dossier+"/" + fileName);
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
