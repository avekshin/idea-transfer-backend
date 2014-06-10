package ua.avekshin.web.optimization.ideatransfer.backend.method;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import ua.avekshin.web.optimization.ideatransfer.backend.method.simulation.SimulationData;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Oleksii
 * Date: 07.01.14
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */
public class EffectCalculationImpl implements EffectCalculation {
    private EffectInputData task;


    private SimulationData simulationData;

    private StartPointCalculator startPointCalculator;

    public EffectCalculationImpl(EffectInputData inputData) {
        this.task = inputData;
    }


    public boolean optimize() throws IllegalArgumentException {
        // этап 2
        List<Float> a = calculateA();

        // этап 3
        List<Float> q = calculateQ1(a, task.getD());

        // этап 4
        List<Float> l = calculateL(q, task.getL0());

        // этап 5
        q = calculateQ2(a, task.getD(), l, simulationData.getL(), task.getL0());

        // этап 6
        List<Float> lCred = calculateLCred(l, simulationData.getL(), q, task.getL0());

        // этап 7
        List<Float> n = getN(simulationData.getLPF(), simulationData.getVPF());
        List<Float> qMinus = getQMinus(lCred, n);

        // Этап 8
        float effect = calculateEffect(simulationData.getQS(), q, qMinus);

        // Этап 9
        List<Float> qNew = calculateQnew(task, q);

        // Этап 10
        List<Float> kRetiers = calculateKRetiers(qNew, simulationData.getVPF(), simulationData.getKjPF(), task);

        // Этап 11
        List<Float> qNewII = calculateQNeqII(kRetiers, qNew, task);
        float dCosts = calculateDCosts(task.getC(), task.getcNewA(), qNewII);

        // Этап 12


        return true;
    }


    private float calculateDCosts(List<Float> cA, List<Float> cNewA, List<Float> qNewII) {
        float dCosts = 0;

        for (int i = 0; i < cA.size(); ++i) {
            dCosts += (cA.get(i) - cNewA.get(i)) * qNewII.get(i);
        }

        return dCosts;
    }

    /**
     * Этап 2
     *
     * @return A - производственные мощности отрасли-производителя
     */
    private List<Float> calculateA() {
        List<Float> a = Lists.newArrayList();
        for (int i = task.getTau3() + 2; i < task.getT(); ++i) {
            float ai = task.getZ3();
            for (int k = task.getTau3() + 1; k < task.getT() - 1; ++k) {
                ai += task.getInv().get(k) / task.getM().get(k);
            }
            a.add(ai);
        }
        return a;
    }

    /**
     * @return А0 - номинальная мощность промышленного предприятия
     */
    private float calculateA0() {
        return this.task.getZ3();
    }

    /**
     * Этап 3
     *
     * @param a
     * @param d
     * @return Q - объем выпуска инновационной продукции
     */
    private List<Float> calculateQ1(List<Float> a, List<Float> d) {
        List<Float> q = Lists.newArrayList();
        for (int i = 0; i < a.size(); ++i) {
            if (a.get(i) <= d.get(i)) {
                q.add(d.get(i));
            } else {
                q.add(a.get(i));
            }
        }
        return q;
    }

    /**
     * Этап 4
     *
     * @param q
     * @param l0
     * @return L - количество кадров, требующихся для выпуска новой продукции
     */
    private List<Float> calculateL(List<Float> q, float l0) {
        List<Float> l = Lists.newArrayList();
        for (float li : q) l.add(l0 * li);
        return l;
    }

    /**
     * Этап 5
     *
     * @param a
     * @param d
     * @param lSim
     * @return Q величины выпуска новой продукции с учетом доступности кадров;
     */
    private List<Float> calculateQ2(List<Float> a, List<Float> d, List<Float> l, List<Float> lSim, float l0) {
        List<Float> q = Lists.newArrayList();
        for (int i = 0; i < a.size(); ++i) {
            float lSimI = lSim.get(i);
            float lI = l.get(i);
            float aI = a.get(i);
            float dI = d.get(i);
            q.add(calculateQi(l0, lSimI, lI, aI, dI));
        }
        return q;
    }

    /**
     * @param l0
     * @param lSimI
     * @param lI
     * @param aI
     * @param dI
     * @return - Qi величины выпуска новой продукции с учетом доступности кадров по годам
     */
    private float calculateQi(float l0, float lSimI, float lI, float aI, float dI) {
        float qi = 0;
        if (lSimI < 0 && lI > -lSimI) {
            qi = -lSimI / l0;
        } else if (lSimI > 0) {
            qi = 0;
        } else if (lSimI < 0 && lI <= lSimI) {
            qi = (aI < dI) ? aI : dI;
        }
        return qi;
    }

    /**
     * Этап 6
     *
     * @param l
     * @param lSim
     * @param q
     * @param l0
     * @return Lзаим - количество занятых средств
     */
    private List<Float> calculateLCred(List<Float> l, List<Float> lSim, List<Float> q, float l0) {
        List<Float> lCred = Lists.newLinkedList();
        for (int i = 0; i < l.size(); ++i) {
            float lSimI = lSim.get(i);
            float lI = l.get(i);
            float qI = q.get(i);
            lCred.add(calculateLCredI(l0, lSimI, lI, qI));
        }
        return lCred;
    }

    /**
     * @param l0
     * @param lSimI
     * @param lI
     * @param qI
     * @return LзаимI - количество занятых средств для конкретного года
     */
    private float calculateLCredI(float l0, float lSimI, float lI, float qI) {
        float lCredI = 0;
        if (lSimI >= 0) {
            lCredI = l0 * qI;
        } else if (lSimI < 0 && lI > -lSimI) {
            lCredI = lI + lCredI;
        } else if (lSimI < 0 && lI <= -lSimI) {
            lCredI = 0;
        }
        return lCredI;
    }

    /**
     * Этап 7
     *
     * @param lCred
     * @param n
     * @return QMinus - потенциально недовыполненный отраслью производителем объем продукции по годам
     */
    private List<Float> getQMinus(List<Float> lCred, List<Float> n) {
        List<Float> qMinus = Lists.newArrayList();
        for (int i = 0; i < lCred.size(); ++i) {
            qMinus.add(lCred.get(i) / n.get(i));
        }
        return qMinus;
    }

    private List<Float> getN(List<Float> lPFSim, List<Float> vPFSim) {
        List<Float> n = Lists.newArrayList();
        for (int i = 0; i < lPFSim.size(); ++i) {
            n.add(lPFSim.get(i) / vPFSim.get(i));
        }
        return n;
    }

    /**
     * @param qSim
     * @param q
     * @param qMinus
     * @return - EFFECT - экономический эффект от внедрения новой технологии в отрасль производителя
     */
    private float calculateEffect(List<Float> qSim, List<Float> q, List<Float> qMinus) {
        float effect = 0;
        effect += VectorUtil.vectorProduct(qSim, q);
        // TODO : fix position about z;
        effect -= VectorUtil.vectorProduct(q, task.getC()) - VectorUtil.sum(this.task.getInv());//  - Lambda.sum(task.get)
        effect -= VectorUtil.vectorProduct(qSim, qMinus);
        return effect;
    }

    private List<Float> calculateQnew(EffectInputData inputData, List<Float> q) {
        List<Float> qNew = Lists.newArrayList();
        // TODO : check indexes, it looks like has to be rewrited
        for (int i = startPointCalculator.getDeltaTBasedStartIndex(inputData); i < q.size(); ++i) {
            qNew.add(task.getFp() * q.get(i));
        }
        return qNew;
    }

    private List<Float> calculateKRetiers(List<Float> qNew, List<Float> vPf, List<Float> kPf, EffectInputData task) {
        List<Float> kRetiers = Lists.newArrayList();
        float kRetiersI = 0;
        float fp;

        for (int i = 0; i < qNew.size(); ++i) {
            if (task.isFoundDecrease()) {
                fp = vPf.get(i) / kPf.get(i);
                kRetiersI = qNew.get(i) / fp;
            } else {
                kRetiersI = 0;
            }
            kRetiers.add(kRetiersI);
        }
        return kRetiers;

    }

    private List<Float> calculateQNeqII(List<Float> kRetiers, List<Float> qNew, EffectInputData task) {
        // TODO: check dt calculation
        // int dt = 0;
        List<Float> qNewII = Lists.newArrayList();

        for (int r = startPointCalculator.getDeltaTBasedStartIndex(task); r < qNew.size(); ++r) {
            qNewII.add(task.getBeta().get(r) * qNew.get(r));
        }

        return qNewII;
    }

    @Autowired
    public void setSimulationData(SimulationData simulationData) {
        this.simulationData = simulationData;
    }

    @Autowired
    public void setStartPointCalculator(StartPointCalculator startPointCalculator) {
        this.startPointCalculator = startPointCalculator;
    }


}