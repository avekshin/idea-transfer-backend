package ua.avekshin.web.optimization.ideatransfer.backend.method.simulation;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Oleksii
 * Date: 09.06.14
 * Time: 17:36
 * To change this template use File | Settings | File Templates.
 */
@Component
public interface SimulationData {

    public List<Float> getL();

    public List<Float> getLPF();

    public List<Float> getVPF();

    public List<Float> getQS();

    public List<Float> getKjPF();
}
