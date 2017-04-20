package com.DDS_2017_k3052_grupo1.proyectoInversion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Loader {
	
	public void leerArchivo(String pathArchivo){
		
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
        File f = null;
        FileReader fr =null;
        BufferedReader br = null;
        
        try {
        	
        f = new File (pathArchivo);
        fr = new FileReader (f);
        br = new BufferedReader (fr);
        listaEmpresas = new ArrayList<Empresa> ();
        String linea;
        
	        while ( (linea = br.readLine()) != null ) {
	        	
	            String[] partesDelString = linea.split(";");
	            String s = partesDelString[0];
	            String d = partesDelString[1];
	            
	            Empresa emp = new Empresa (0,s,d);
	            listaEmpresas.add (emp);
	        }
        } catch (FileNotFoundException fnfe) {System.out.println("error1"); } catch (IOException ioe) {System.out.println("error2");}
        
        finally {
            try{
                if (fr != null)
            fr.close();}
            catch (IOException ioe) {System.out.println("error3");}
            if (listaEmpresas != null)
            for (Empresa empresa : listaEmpresas) {
            System.out.println (" Nombre :   "+ empresa.getNombre() + "  Cuenta:   "+ empresa.getCuenta() );
        }
        }
    

	}

}
