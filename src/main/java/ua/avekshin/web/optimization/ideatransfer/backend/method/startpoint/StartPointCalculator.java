package ua.avekshin.web.optimization.ideatransfer.backend.method.startpoint;

import ua.avekshin.web.optimization.ideatransfer.backend.model.EffectInputData;

/**
 * Created with IntelliJ IDEA.
 * User: Oleksii
 * Date: 10.06.14
 * Time: 22:00
 * To change this template use File | Settings | File Templates.
 */
public interface StartPointCalculator {

    public int getDeltaTBasedStartIndex(EffectInputData task);

}
