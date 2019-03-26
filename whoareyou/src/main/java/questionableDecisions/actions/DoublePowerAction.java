package questionableDecisions.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DoublePowerAction extends AbstractGameAction {

    @Override
    public void update() {
        AbstractPower p = AbstractDungeon.player.powers.get(AbstractDungeon.cardRng.random(AbstractDungeon.player.powers.size() - 1));
        p.amount *= 2;
        p.flash();
        isDone = true;
    }
}
