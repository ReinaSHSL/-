package questionableDecisions.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import questionableDecisions.MORECHAOSMOREPOWER;
import questionableDecisions.cards.AbstractWtfCard;

public class RerollHandNumbersAction extends AbstractGameAction {

    @Override
    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            c.cost = AbstractDungeon.cardRng.random(3);
            c.costForTurn = c.cost;
            c.baseDamage = MORECHAOSMOREPOWER.getDamage(c.cost);
            c.baseBlock = MORECHAOSMOREPOWER.getBlock(c.cost);
            c.baseMagicNumber = MORECHAOSMOREPOWER.getMagic(c.cost);
            c.resetAttributes();
            c.magicNumber = c.baseMagicNumber;
            isDone = true;
        }
    }
}
