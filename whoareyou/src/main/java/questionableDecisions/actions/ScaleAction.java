package questionableDecisions.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import questionableDecisions.cards.AbstractWtfCard;

public class ScaleAction extends AbstractGameAction {
    AbstractCard c;

    public ScaleAction(AbstractCard c) {
        this.c = c;
    }

    @Override
    public void update() {
        c.baseDamage += 1;
        c.baseBlock += 1;
        c.baseMagicNumber += 1;
        if (c instanceof AbstractWtfCard) {
            ((AbstractWtfCard)c).baseWtfMagicNumber += 1;
        }
        isDone = true;
    }
}
