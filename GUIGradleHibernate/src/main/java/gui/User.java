package gui;

public class User {
    private String username;
    private int wallet;
    private int chart;

    public User(){
        this.username="test";
        this.chart=1;
        this.wallet= 1000;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getChart() {
        return chart;
    }

    public void setChart(int chart) {
        this.chart = chart;
    }
}
