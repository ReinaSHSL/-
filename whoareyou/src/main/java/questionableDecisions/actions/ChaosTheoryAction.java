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
        ArrayList<MORECHAOSMOREPOWER.Components> allPossibleCardDesc = new ArrayList<>();
        for (MORECHAOSMOREPOWER.Components cd : MORECHAOSMOREPOWER.Components.values()) {
            if (!cd.name().startsWith("WHEN_")) {
                allPossibleCardDesc.add(cd);
            }
        }
        Collections.shuffle(allPossibleCardDesc);
        int weight = 80;
        int loseWeight = 30;

        int cost = AbstractDungeon.cardRng.random(3);
        int d = getDamage(cost);
        int b = getBlock(cost);
        int magic = getMagic(cost);
        int wtfMagic = getMagic(cost);
        int du = AbstractDungeon.cardRng.random(3, 5);
        int bu = AbstractDungeon.cardRng.random(3, 5);
        int cu = AbstractDungeon.cardRng.random(cost);
        int mu = AbstractDungeon.cardRng.random(0, 1);
        int muu = AbstractDungeon.cardRng.random(0, 1);

        AbstractWtfCard c = new AbstractWtfCard("",cost, AbstractCard.CardRarity.SPECIAL, cu, du, bu, mu, muu, d, b, magic, wtfMagic) {
        };

        for (MORECHAOSMOREPOWER.Components de : allPossibleCardDesc) {
            int rng = AbstractDungeon.cardRng.random(99);
            if (rng < weight) {
                c.componentList.add(de);
                weight -= loseWeight;
                if (weight <= 0) {
                    weight = 5;
                }
            }
        }
        c.setCardInfo();
        c.resetAttributes();
        c.magicNumber = c.baseMagicNumber;
        c.wtfMagicNumber = c.baseWtfMagicNumber;
        c.buildID();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
        isDone = true;
    }

    private int getDamage(int cost) {
        switch (cost) {
            case 0:
                return AbstractDungeon.cardRng.random(3, 5);
            case 1:
                return AbstractDungeon.cardRng.random(9, 12);
            case 2:
                return AbstractDungeon.cardRng.random(17, 24);
            case 3:
                return AbstractDungeon.cardRng.random(26, 36);
        }
        return 0;
    }

    private int getBlock(int cost) {
        switch (cost) {
            case 0:
                return AbstractDungeon.cardRng.random(3, 5);
            case 1:
                return AbstractDungeon.cardRng.random(7, 9);
            case 2:
                return AbstractDungeon.cardRng.random(14, 20);
            case 3:
                return AbstractDungeon.cardRng.random(24, 36);
        }
        return 0;
    }

    private int getMagic(int cost) {
        switch (cost) {
            case 0:
                return AbstractDungeon.cardRng.random(1, 1);
            case 1:
                return AbstractDungeon.cardRng.random(1, 2);
            case 2:
                return AbstractDungeon.cardRng.random(2, 3);
            case 3:
                return AbstractDungeon.cardRng.random(3, 5);
        }
        return 0;
    }
}
