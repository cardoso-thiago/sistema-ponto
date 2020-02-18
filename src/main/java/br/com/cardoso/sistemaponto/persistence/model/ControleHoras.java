package br.com.cardoso.sistemaponto.persistence.model;

import org.joda.time.DateTime;

import java.util.List;

public class ControleHoras {
    private List<BatidaPonto> batidaPontoList;
    private String horasTrabalhadas;

    public List<BatidaPonto> getBatidaPontoList() {
        return batidaPontoList;
    }

    public void setBatidaPontoList(List<BatidaPonto> batidaPontoList) {
        this.batidaPontoList = batidaPontoList;
    }

    public String getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(String horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }
}
