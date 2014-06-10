package ua.avekshin.web.optimization.ideatransfer.backend.method;

import com.google.common.collect.Lists;

import java.util.List;

public class EffectInputData {

    /**
     * начальный год реализации проекта
     */
    private int t0;
    /**
     * продолжмтельность планового периода
     */
    private int t;
    /**
     * продолжительность этапа НДР
     */
    private int tau1;
    /**
     * продолжительность этапа ДКР
     */
    private int tau2;
    /**
     * продолжительность этапа подготовки производства
     */
    private int tau3;
    /**
     * величина затрат на НДР
     */
    private float z1;
    /**
     * величина затрат на ДКР
     */
    private float z2;
    /**
     * одноразовые затраты для создания производства
     */
    private float z3;
    /**
     * величина спроса по годам на новую продукцию
     */
    private List<Float> d;
    /**
     * стоимость единицы новой продукции по годам
     */
    private List<Float> c;
    /**
     * инвестиции, которые планируются в производстве области производителя по годам
     */
    private List<Float> inv;
    /**
     * количество кадров, необходимых для производства единицы продукции
     */
    private int l0;
    /**
     * продуктивность основных фондов
     */
    private float fp;
    /**
     * степень загруженности основных фондов по годам
     */
    private List<Float> beta;
    /**
     * величина переменных затрат на производство единицы продукции в j-му году с использованием нових фондов
     */
    private List<Float> cNewA;
    /**
     * трудоемкость производства единицы продукции с использованием новых производственных фондов
     */
    private float n;

    private List<Float> m;

    private boolean isFoundDecrease;

    public EffectInputData() {
        this.d = Lists.newArrayList();
        this.c = Lists.newArrayList();
        this.inv = Lists.newArrayList();
        this.beta = Lists.newArrayList();
        this.cNewA = Lists.newArrayList();
    }

    public int getT0() {
        return t0;
    }

    public void setT0(int t0) {
        this.t0 = t0;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getTau1() {
        return tau1;
    }

    public void setTau1(int tau1) {
        this.tau1 = tau1;
    }

    public int getTau2() {
        return tau2;
    }

    public void setTau2(int tau2) {
        this.tau2 = tau2;
    }

    public int getTau3() {
        return tau3;
    }

    public void setTau3(int tau3) {
        this.tau3 = tau3;
    }

    public float getZ1() {
        return z1;
    }

    public void setZ1(float z1) {
        this.z1 = z1;
    }

    public float getZ2() {
        return z2;
    }

    public void setZ2(float z2) {
        this.z2 = z2;
    }

    public float getZ3() {
        return z3;
    }

    public void setZ3(float z3) {
        this.z3 = z3;
    }

    public List<Float> getD() {
        return d;
    }

    public void setD(List<Float> d) {
        this.d = d;
    }

    public List<Float> getC() {
        return c;
    }

    public void setC(List<Float> c) {
        this.c = c;
    }

    public List<Float> getInv() {
        return inv;
    }

    public void setInv(List<Float> inv) {
        this.inv = inv;
    }

    public int getL0() {
        return l0;
    }

    public void setL0(int l0) {
        this.l0 = l0;
    }

    public float getFp() {
        return fp;
    }

    public void setFp(float fp) {
        this.fp = fp;
    }

    public List<Float> getBeta() {
        return beta;
    }

    public void setBeta(List<Float> beta) {
        this.beta = beta;
    }

    public List<Float> getcNewA() {
        return cNewA;
    }

    public void setcNewA(List<Float> cNewA) {
        this.cNewA = cNewA;
    }

    public float getN() {
        return n;
    }

    public void setN(float n) {
        this.n = n;
    }

    public List<Float> getM() {
        return m;
    }

    public void setM(List<Float> m) {
        this.m = m;
    }

    public boolean isFoundDecrease() {
        return isFoundDecrease;
    }

    public void setFoundDecrease(boolean foundDecrease) {
        isFoundDecrease = foundDecrease;
    }
}