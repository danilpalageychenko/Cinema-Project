package model;

/**
 * Created by gaara on 3/10/17.
 */
public class Ticket extends Entity {

    private long seasonId;
    private long userId;

    public Ticket()
    {

    }

    public Ticket(long seasonId, long userId) {
        setSeasonId(seasonId);
        setUserId(userId);
    }

    public long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(long seasonId) {
        this.seasonId = seasonId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
