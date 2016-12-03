import com.gs.bean.Product;
import com.gs.parser.GOMEProductParser;
import com.gs.parser.JDProductParser;
import com.gs.parser.SUNINGProductParser;
import com.gs.parser.TMALLProductParser;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

/**
 * Created by WangGenshen on 11/28/16.
 */
public class ProductParserTest extends TestCase {

    private TMALLProductParser tmallParser;
    private JDProductParser jdParser;
    private SUNINGProductParser snParser;
    private GOMEProductParser gmParser;

    @Override
    protected void setUp() throws Exception {
        tmallParser = new TMALLProductParser();
        jdParser = new JDProductParser();
        snParser = new SUNINGProductParser();
        gmParser = new GOMEProductParser();
    }

    @Test
    public void testTMALLParsetList() {
        List<Product> productList = tmallParser.parserList("联想", 10);
        for (Product p : productList) {
            System.out.println(p);
        }
    }

    @Test
    public void testJDParser() {
        List<Product> productList = jdParser.parserList("联想", 10);
        for (Product p : productList) {
            System.out.println(p);
        }
    }

    @Test
    public void testSNParser() {
        List<Product> productList = snParser.parserList("联想", 10);
        for (Product p : productList) {
            System.out.println(p);
        }
    }

    @Test
    public void testGMParser() {
        List<Product> productList = gmParser.parserList("联想", 10);
        for (Product p : productList) {
            System.out.println(p);
        }
    }
}
