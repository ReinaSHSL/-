package questionableDecisions.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import questionableDecisions.MORECHAOSMOREPOWER;
import questionableDecisions.cards.AbstractWtfCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ChaosTheoryAction extends AbstractGameAction {

    @Override
    public void update() {
        AbstractCard c = MORECHAOSMOREPOWER.generateCard();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
        isDone = true;
    }

}
