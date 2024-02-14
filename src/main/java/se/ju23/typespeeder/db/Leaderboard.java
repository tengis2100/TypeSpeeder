package se.ju23.typespeeder.db;

import jakarta.persistence.*;

@Entity
public class Leaderboard {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "leaderboard_id", nullable = false)
    private long leaderboardId;
    @Basic
    @Column(name = "average", nullable = true, precision = 0)
    private Double average;
    @Basic
    @Column(name = "speed", nullable = true, precision = 0)
    private Double speed;
    @Basic
    @Column(name = "mostrights", nullable = true)
    private Integer mostrights;
    @Basic
    @Column(name = "mostright_inorder", nullable = true, length = 45)
    private String mostrightInorder;
    @Basic
    @Column(name = "resultcol", nullable = true, length = 45)
    private String resultcol;
    @Basic
    @Column(name = "playerid", nullable = true)
    private Long playerid;

    public long getLeaderboardId() {
        return leaderboardId;
    }

    public void setLeaderboardId(long leaderboardId) {
        this.leaderboardId = leaderboardId;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getMostrights() {
        return mostrights;
    }

    public void setMostrights(Integer mostrights) {
        this.mostrights = mostrights;
    }

    public String getMostrightInorder() {
        return mostrightInorder;
    }

    public void setMostrightInorder(String mostrightInorder) {
        this.mostrightInorder = mostrightInorder;
    }

    public String getResultcol() {
        return resultcol;
    }

    public void setResultcol(String resultcol) {
        this.resultcol = resultcol;
    }

    public Long getPlayerid() {
        return playerid;
    }

    public void setPlayerid(Long playerid) {
        this.playerid = playerid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Leaderboard that = (Leaderboard) o;

        if (leaderboardId != that.leaderboardId) return false;
        if (average != null ? !average.equals(that.average) : that.average != null) return false;
        if (speed != null ? !speed.equals(that.speed) : that.speed != null) return false;
        if (mostrights != null ? !mostrights.equals(that.mostrights) : that.mostrights != null) return false;
        if (mostrightInorder != null ? !mostrightInorder.equals(that.mostrightInorder) : that.mostrightInorder != null)
            return false;
        if (resultcol != null ? !resultcol.equals(that.resultcol) : that.resultcol != null) return false;
        if (playerid != null ? !playerid.equals(that.playerid) : that.playerid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (leaderboardId ^ (leaderboardId >>> 32));
        result = 31 * result + (average != null ? average.hashCode() : 0);
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        result = 31 * result + (mostrights != null ? mostrights.hashCode() : 0);
        result = 31 * result + (mostrightInorder != null ? mostrightInorder.hashCode() : 0);
        result = 31 * result + (resultcol != null ? resultcol.hashCode() : 0);
        result = 31 * result + (playerid != null ? playerid.hashCode() : 0);
        return result;
    }
}
