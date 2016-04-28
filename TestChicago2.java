import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
	//import org.openqa.selenium.firefox.FirefoxDriver;

public class TestChicago {
	

public static final Integer WAIT_TIME = 2000;
    
    
    public static void main(String[] args) throws IOException, InterruptedException {
    	
    	//System.setProperty("webdriver.firefox.bin", "D:\\NewProgram\\Mozilla\\firefox.exe");
    	
	    //WebDriver driver = new FirefoxDriver();
	    
	    //System.setProperty("webdriver.ie.driver", "C:\\selenium-2.51.0\\IEDriverServer.exe");	 
    	


     // For work in IE you must remove PROTECTED MODE from all zones !!!
    /*  System.setProperty("webdriver.ie.driver", "C:\\selenium-2.51.0\\IEDriverServer.exe");
        
        WebDriver driver = new InternetExplorerDriver();*/
        
        System.setProperty("Webdriver.chrome.driver", "C:\\selenium-2.51.0\\chromedriver.exe");
        
         DesiredCapabilities capabilities = DesiredCapabilities.chrome();
          capabilities.setCapability("chrome.switches", Arrays.asList("--load-extension=C:\\Users\\admin\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions\\"));
          WebDriver driver = new ChromeDriver(capabilities);
        
        

        
        String appUrl = "http://www.remax1stclass.com/homes-for-sale";    
        //String appUrl = "http://localhost:4444/apartments-for-rent";
        //String appUrl = "http://localhost:4444/homes-for-sale";    
        
         driver.get(appUrl);
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         Thread.sleep(WAIT_TIME);
        
         String totalCity = driver.findElement(By.className("listingsTotal")).getText();    
         String totalCity_str = totalCity.replaceAll("[^\\d.]", "");  
         int  totalCity_int = Integer.parseInt(totalCity_str);
        
         int totalCity_split = 0;
        
         List <WebElement> ElementCity = driver.findElements(By.className("cityBox"));
         for (int k = 0; k < ElementCity.size(); k++) {
             Thread.sleep(WAIT_TIME);
             ElementCity = driver.findElements(By.className("cityBox"));            
             ElementCity.get(k).click();
             Thread.sleep(WAIT_TIME);
             String actualTitle = driver.getTitle();
                     
             String AllCity = driver.findElement(By.className("allCityListingsTotal")).getText();        
             int AllCity_int = Integer.parseInt(AllCity);    
            
             
                 String AllCityNum_str = driver.findElement(By.className("allCityListingsTotal")).getText();
                 AllCityNum_str = AllCityNum_str.replaceAll("[^\\d.]", "");  
                 int AllCityNum_int = Integer.parseInt(AllCityNum_str);
                 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);    
                 int AllCityNumByZip_int3 = 0;
                 Thread.sleep(WAIT_TIME);
                 List <WebElement> Element = driver.findElements(By.className("zipNameBox"));         
                 for (int j = 0; j < Element.size(); j++) {
                     Thread.sleep(WAIT_TIME);
                     Element = driver.findElements(By.className("zipNameBox"));                 
                     Thread.sleep(WAIT_TIME);                           
                     String AllCityNum_str2 = Element.get(j).getText();                 
                     Element.get(j).click();
                     Thread.sleep(WAIT_TIME);
                     String AllCityNum_str1 = driver.findElement(By.className("listingsTotal")).getText();
                     AllCityNum_str1 = AllCityNum_str1.replaceAll("[^\\d.]", "");
                     int AllCityNum_int1 = Integer.parseInt(AllCityNum_str1);
                     List<WebElement> drop = driver.findElements(By.className("zipListingsNum"));           
                     java.util.Iterator<WebElement> i = drop.iterator();
                     int AllCityNumByZip_int = 0;                 
                     while(i.hasNext()) {
                         Thread.sleep(WAIT_TIME);
                             WebElement row = i.next();   
                         String AllCityNumByZip_str = row.getText().replaceAll("\\p{P}","");
                         AllCityNumByZip_int = Integer.parseInt(AllCityNumByZip_str) + AllCityNumByZip_int;
                                         }     
                     if (AllCityNumByZip_int == AllCityNum_int1)  {
                     System.out.println(actualTitle+" splitted by type zip test: "+AllCityNum_str2+" passed "+AllCityNum_int1+"=="+AllCityNumByZip_int);
                                                                  } else {
                             System.out.println(actualTitle+" splitted by type zip test: "+AllCityNum_str2+" failed "+AllCityNum_int1+"!="+AllCityNumByZip_int);
                             }
                     AllCityNumByZip_int3 += AllCityNum_int1;
                     Thread.sleep(WAIT_TIME);             
                     driver.navigate().back();                 
                     Thread.sleep(WAIT_TIME);
                                                             } // of for (int k = 29; k < 33; k++)
                     if (AllCityNumByZip_int3 == AllCityNum_int)  {
                         System.out.println(actualTitle+" splitted by zips city test: passed "+AllCityNumByZip_int3+"=="+AllCityNum_int);
                                                                  } else {
                         System.out.println(actualTitle+" splitted by zips city test: failed "+AllCityNumByZip_int3+"!="+AllCityNum_int);
                                                                          }
                     Thread.sleep(WAIT_TIME);  
                     
                     
                     driver.navigate().back();        
                     //Thread.sleep(WAIT_TIME);
                     
                     totalCity_split += AllCity_int;
                     
                         }
        
                 if (totalCity_split == totalCity_int)  {
                System.out.println("all listings splitted by cities test: passed "+totalCity_split+"=="+totalCity_int);
                 } else {
                 System.out.println("all listings splitted by cities test: failed "+totalCity_split+"!="+totalCity_int);
                         }
    
                }
    
    
        }