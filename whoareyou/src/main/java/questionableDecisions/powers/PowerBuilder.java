package questionableDecisions.powers;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import questionableDecisions.MORECHAOSMOREPOWER;

import java.util.ArrayList;
import java.util.Map;
import java.util.MissingFormatArgumentException;

import static questionableDecisions.MORECHAOSMOREPOWER.makeID;
import static questionableDecisions.util.TextureLoader.getTexture;

public class PowerBuilder {

    public static godpleaseendme buildPower(String ID, String name, String desc, AbstractCreature owner, int amount, boolean reduce, ArrayList<MORECHAOSMOREPOWER.Components> c) {
        if (!c.get(0).name().startsWith("WHEN_")) {
            throw new RuntimeException("Wtf add a timing");
        }
        godpleaseendme p = new godpleaseendme(makeID(ID), name, owner, amount, c, reduce) {

            @Override
            public void updateDescription()
            {
                String powerDesc = desc.replace("!M!", "%d");
                powerDesc = powerDesc.replace("!D!", "%d");
                powerDesc = powerDesc.replace("!B!", "%d");
                description = String.format(powerDesc, this.amount);
            }
        };
        p.img = getTexture("questionableDecisionsResources/images/ui/missing_texture.png");
        p.updateDescription();
        return p;
    }
    
    public static godpleaseendme buildPower(String ID, String name, String desc, AbstractCreature owner, Map<String, Integer> amounts, boolean reduce, ArrayList<MORECHAOSMOREPOWER.Components> c) {
        if (!c.get(0).name().startsWith("WHEN_")) {
            throw new RuntimeException("Wtf add a timing");
        }
        godpleaseendme p = new godpleaseendme(makeID(ID), name, owner, amounts, c, reduce) {

            @Override
            public void updateDescription()
            {
                String powerDesc = desc.replace("!M!", amounts.getOrDefault("magicNumber", 0).toString());
                powerDesc = powerDesc.replace("!D!", amounts.getOrDefault("damage", 0).toString());
                powerDesc = powerDesc.replace("!B!", amounts.getOrDefault("block", 0).toString());
                description = powerDesc;
            }
        };
        p.img = getTexture("questionableDecisionsResources/images/ui/missing_texture.png");
        p.updateDescription();
        return p;
    }
}
