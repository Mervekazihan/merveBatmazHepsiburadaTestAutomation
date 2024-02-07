import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static util.Constants.*;

public class ApiTest {
    BufferedWriter writer;
    DateFormat dateFormat;
    Date date;

    @BeforeTest
    public void before() throws IOException {
        writer = new BufferedWriter(new FileWriter(REPORT_FILE_NAME));
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date = new Date();

    }
    @Description("Check HTTP GET status")
    @Test
    public void checkHttpStatus () throws UnirestException, IOException {
        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/boards/rJY1mQ5T/memberships")
                .header("Accept", "application/json")
                .queryString("key", API_KEY)
                .queryString("token", API_TOKEN)
                .asJson();

        if (response.getStatus() == 200) {
            writer.write("Api GET Response : " + response.getBody() +
                    " Api GET Status : " + response.getStatus() + " BAŞARILI " +
                    dateFormat.format(date) + " \n");
        } else {
            writer.write("Api GET Response : " + response.getBody() +
                    " Api GET Status : " + response.getStatus() + " BAŞARISIZ " +
                    dateFormat.format(date) + " \n");
        }
        Assert.assertEquals(response.getStatus(), 200);
    }

    @Description("Check HTTP POST status")
    @Test
    public void checkHttpPostStatus () throws UnirestException, IOException {
        HttpResponse<String> response = Unirest.post("https://api.trello.com/1/boards/rJY1mQ5T/labels")
                .queryString("name", "Merve Test Post")
                .queryString("color", "red")
                .queryString("key", API_KEY)
                .queryString("token", API_TOKEN)
                .asString();

        if (response.getStatus() == 200) {
            writer.write("Api POST Response : " + response.getBody() +
                    " Api POST Status : " + response.getStatus() + " BAŞARILI " +
                    dateFormat.format(date) + " \n");
        } else {
            writer.write("Api POST Response : " + response.getBody() +
                    " Api POST Status : " + response.getStatus() + " BAŞARISIZ " +
                    dateFormat.format(date) + " \n");
        }
        Assert.assertEquals(response.getStatus(), 200);
    }

    @AfterTest
    public void after () throws IOException {
        writer.close();
    }
}
