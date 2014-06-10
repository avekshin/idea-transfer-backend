package ua.avekshin.web.optimization.ideatransfer.backend.method;

/**
 * Created with IntelliJ IDEA.
 * User: Oleksii
 * Date: 10.06.14
 * Time: 22:00
 * To change this template use File | Settings | File Templates.
 */
public class StartPointCalculatorImpl implements StartPointCalculator {

    public static final int DELTA_T = 1;

    @Override
    public int getDeltaTBasedStartIndex(EffectInputData task) {
        return task.getTau3() + StartPointCalculatorImpl.DELTA_T + 1;
    }
}
