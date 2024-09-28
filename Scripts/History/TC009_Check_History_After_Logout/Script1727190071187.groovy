import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Make_Appointment/TC006_Appointment_Success'), [('facility') : 'Seoul CURA Healthcare Center'
        , ('readmission') : 'yes', ('program') : 'Medicaid', ('visit_date') : '28/09/24', ('past_date') : '01/01/22', ('notes') : 'sakit'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/a_CURA Healthcare_menu-toggle'))

WebUI.click(findTestObject('Page_CURA Healthcare Service/a_Logout'))

WebUI.callTestCase(findTestCase('Login/TC001_Login_With_Valid_Credential'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/a_CURA Healthcare_menu-toggle'))

WebUI.click(findTestObject('Object Repository/Page_CURA Healthcare Service/a_History'))

boolean isAppointmentPresent = WebUI.verifyElementPresent(findTestObject('Object Repository/Page_CURA Healthcare Service/p_Seoul CURA Healthcare Center'), 
    10)

boolean isnotAppointmentPresent = WebUI.verifyElementPresent(findTestObject('Page_CURA Healthcare Service/p_No appointment'), 
    10)

WebUI.takeScreenshot()

if (isnotAppointmentPresent) {
    WebUI.comment('Test Failed')

    assert false : 'Test failed: Seharusnya pengguna  dapat history appointment yang telah dibuat sebelumnya.'
} else if (isAppointmentPresent) {
    WebUI.comment('Test Passed: Appoinment yang telah dibuat berhasil ditampilkan')
}

