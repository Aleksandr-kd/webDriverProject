package components.blocks;

import common.AbsCommon;
import org.openqa.selenium.WebDriver;

/**
 * Здесь пишутся методы как будто для страницы
 * @Component("css:.component") css-стратегия поиска, component - значение. (By locator)
 */

public class NewsBlock extends AbsCommon {

    public NewsBlock(WebDriver driver) {
        super(driver);
    }
}
