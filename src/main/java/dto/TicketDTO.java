package dto;
import model.Entity;
import model.Season;

/**
 * Created by gaara on 4/7/17.
 */

public class TicketDTO extends Entity {

    private long seasonId;
    private long userId;

    public TicketDTO()
    {

    }

    public TicketDTO(long seasonId, long userId) {
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

