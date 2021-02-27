package com.vobi.devops.bank.ui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.seljup.BrowserType;
import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;

class CalculadoraPesoIdealUI {

	@RegisterExtension
	static SeleniumJupiter seleniumJupiter = new SeleniumJupiter();
	
	@Test	
     void calcularPesoIdeal_debe_calcular_peso_correctamente(
			
			@DockerBrowser(type = BrowserType.EDGE)
			RemoteWebDriver driver
			
			) {
		
		//Arrange
		driver.get("http://salud.coomeva.com.co/publicaciones.php?id=8338");
		
		String nombreComboEdad = "edad";
		String xpathEdad = "//option[. = '40 a 49 años']";
		
		String nombreComboAltura = "altura";
		String xpathAltura = "//option[. = '1 m 70 cm']";
		
		String nombreCajaTextoResultadoPeso = "pesomedio";
		
		String pesoIdealEsperado = "72.9";
		String pesoIdealObtenido = null;
		
		//Act
		driver.findElement(By.name("sexo")).click();
		
		// Seleccione la edad 40 a 49 anhos
		driver.findElement(By.name(nombreComboEdad)).click();
		WebElement dropdownEdad = driver.findElement(By.name(nombreComboEdad));
		dropdownEdad.findElement(By.xpath(xpathEdad)).click();

		// Seleccione la altura 1 m 70 cm
		driver.findElement(By.name(nombreComboAltura)).click();
		WebElement dropdown = driver.findElement(By.name(nombreComboAltura));
		dropdown.findElement(By.xpath(xpathAltura)).click();

		// Clic en el botón "calculate"
		driver.findElement(By.name("calculate")).click();

		// Obtener el resultado del peso medio
		pesoIdealObtenido = driver.findElement(By.name(nombreCajaTextoResultadoPeso)).getAttribute("value");
		
		//Assert
		assertEquals(pesoIdealObtenido, pesoIdealEsperado);
		
	}
	
	

	
	/*@Test
	void calcularPesoIdeal_debe_calcular_peso_correctamente( ChromeDriver driver ) {
		
		//Arrange
		driver.get("http://salud.coomeva.com.co/publicaciones.php?id=8338");
		
		String nombreComboEdad = "edad";
		String xpathEdad = "//option[. = '40 a 49 años']";
		
		String nombreComboAltura = "altura";
		String xpathAltura = "//option[. = '1 m 70 cm']";
		
		String nombreCajaTextoResultadoPeso = "pesomedio";
		
		String pesoIdealEsperado = "72.9";
		String pesoIdealObtenido = null;
		
		//Act
		driver.findElement(By.name("sexo")).click();
		
		// Seleccione la edad 40 a 49 anhos
		driver.findElement(By.name(nombreComboEdad)).click();
		WebElement dropdownEdad = driver.findElement(By.name(nombreComboEdad));
		dropdownEdad.findElement(By.xpath(xpathEdad)).click();

		// Seleccione la altura 1 m 70 cm
		driver.findElement(By.name(nombreComboAltura)).click();
		WebElement dropdown = driver.findElement(By.name(nombreComboAltura));
		dropdown.findElement(By.xpath(xpathAltura)).click();

		// Clic en el botón "calculate"
		driver.findElement(By.name("calculate")).click();

		// Obtener el resultado del peso medio
		pesoIdealObtenido = driver.findElement(By.name(nombreCajaTextoResultadoPeso)).getAttribute("value");
		
		//Assert
		assertEquals(pesoIdealObtenido, pesoIdealEsperado);
		
	}*/
	
	
	
}
