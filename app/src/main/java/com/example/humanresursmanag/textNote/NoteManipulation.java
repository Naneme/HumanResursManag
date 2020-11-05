package com.example.humanresursmanag.textNote;

import com.example.humanresursmanag.model.Employ;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;

public class NoteManipulation {

    private NoteManipulation() {
    }

    public void fairEmploy(Employ employ) {

        String nameText = employ.getLastName() + " " + employ.getFirsName() + " " + employ.getName();
        String dataText = GregorianCalendar.DAY_OF_WEEK + " " + GregorianCalendar.MONTH +" " + GregorianCalendar.YEAR;

        try(FileOutputStream fos=new FileOutputStream("src/main/java/com/example/humanresursmanag/textNote/Fair"))
        {
            // перевод строки в байты
            byte[] buffer = nameText.getBytes();

            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        System.out.println("The file has been written");
    }

    }

