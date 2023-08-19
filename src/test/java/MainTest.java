import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Disabled;

public class MainTest {
    @Disabled
    @Test
    @Timeout(value = 22)
    public void main() throws Exception {
        Main.main(null);
    }
}
