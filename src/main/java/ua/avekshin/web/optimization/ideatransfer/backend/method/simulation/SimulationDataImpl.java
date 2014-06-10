package ua.avekshin.web.optimization.ideatransfer.backend.method.simulation;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Oleksii
 * Date: 09.06.14
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public class SimulationDataImpl implements SimulationData {
    @Override
    public List<Float> getL() {
        return Lists.newArrayList();
    }

    //  LiПФ
    @Override
    public List<Float> getLPF() {
        return Lists.newArrayList(9018f, 8936f, 9394f, 9822f, 10222f, 10596f, 10935f, 11240f, 11521f);
    }

    //  ViПФ
    @Override
    public List<Float> getVPF() {
        return Lists.newArrayList(2780.48f, 3153.41f, 3103.52f, 3094.03f, 3118.55f, 3174.43f, 3265.58f, 3390.94f, 3545.87f);
    }

    @Override
    public List<Float> getQS() {
        return null;
    }

    @Override
    public List<Float> getKjPF() {
        return Lists.newArrayList(211179f, 432451f, 664270f, 907108f, 1161460f, 1427840f, 1706800f, 1998890f, 2304700f);
    }
}
