package testRunner;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import static org.junit.Assert.*;
import org.junit.Test;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/*
@KarateOptions(features = "src/test/java/",
               //tags ={"@tag7,@get"}
                  tags = "tag9"
)
*/

public class testRunner {


    @Test
    public void testParallel() {
        Results results = Runner.path("/Users/sahabt/Desktop/KarateApiTesting/src/test/java/ParallelTests/").tags("~@ignore").
                parallel(1);
        //assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
        generateReport(results.getReportDir());
        //tags("~@ignore") -> feature ın içindeki bütün testleri aynı anda çalıştırır.
        //Raporlama yapmaya yarıyor.
    }

    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        List<String> jsonPaths = new ArrayList(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "demo");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }

}

