package questionableDecisions.powers;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import questionableDecisions.MORECHAOSMOREPOWER;

import java.util.ArrayList;

import static questionableDecisions.MORECHAOSMOREPOWER.makeID;
import static questionableDecisions.util.TextureLoader.getTexture;

public class PowerBuilder {

    public static godpleaseendme buildPower(String ID, String name, String desc, AbstractCreature owner, int amount, boolean reduce, ArrayList<MORECHAOSMOREPOWER.Components> c) {
        if (!c.get(0).name().startsWith("WHEN_")) {
            throw new RuntimeException("Wtf add a timing");
        }
        godpleaseendme p = new godpleaseendme(makeID(ID), name, owner, amount, c, reduce) {

            @Override
            public void updateDescription() {
                description = desc;
            }
        };
        p.img = getTexture("questionableDecisionsResources/images/ui/missing_texture.png");
        p.updateDescription();
        return p;
    }
}
