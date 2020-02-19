package br.com.cardoso.sistemaponto.persistence.model;

import org.joda.time.Period;
import org.joda.time.Seconds;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

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

    public String converterSegundosEmPeriodo(Seconds seconds) {
        Period period = new Period(seconds);
        PeriodFormatter periodFormatter = new PeriodFormatterBuilder()
                .appendHours()
                .appendSuffix(" hora", " horas")
                .appendSeparator(", ")
                .appendMinutes()
                .appendSuffix(" minuto", " minutos")
                .appendSeparator(" e ")
                .appendSeconds()
                .appendSuffix(" segundo", " segundos")
                .toFormatter();
        return periodFormatter.print(period.normalizedStandard());
    }
}
