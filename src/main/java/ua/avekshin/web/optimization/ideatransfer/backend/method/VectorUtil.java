package ua.avekshin.web.optimization.ideatransfer.backend.method;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Oleksii
 * Date: 07.01.14
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public class VectorUtil {
    public static float vectorProduct(List<Float> a, List<Float> b) {
        float result = 0;
        for (int i = 0; i < a.size(); ++i) {
            result += a.get(i) * b.get(i);
        }
        return result;
    }

    public static float sum(List<Float> inv) {
        float result = 0;
        for (float value : inv) result += value;
        return result;
    }
}
