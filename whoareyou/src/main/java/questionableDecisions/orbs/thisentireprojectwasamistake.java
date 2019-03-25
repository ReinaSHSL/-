package questionableDecisions.orbs;

import basemod.abstracts.CustomOrb;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import questionableDecisions.MORECHAOSMOREPOWER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class thisentireprojectwasamistake extends CustomOrb {
    private ArrayList<MORECHAOSMOREPOWER.Components> components;
    public ArrayList<ArrayList<MORECHAOSMOREPOWER.Components>> effects = new ArrayList<>();
    private static Random r = new Random();

    public thisentireprojectwasamistake(int basePassiveAmount, int baseEvokeAmount, ArrayList<MORECHAOSMOREPOWER.Components> components) {
        super("", generateName(), basePassiveAmount, baseEvokeAmount, generatePassiveDesc(), generateEvokeDesc(), "questionableDecisionsResources/images/Badge.png");
        this.components = components;
        this.ID = buildID();
        buildEffects(components);
    }

    private void activateOrb(ArrayList<MORECHAOSMOREPOWER.Components> effect) {

    }

    public void buildEffects(ArrayList<MORECHAOSMOREPOWER.Components> list) {
        ArrayList<MORECHAOSMOREPOWER.Components> orbEffect = new ArrayList<>();
        for (MORECHAOSMOREPOWER.Components c : list) {
            if (c.name().startsWith("WHEN_") && !checkIfEffectHasTiming(orbEffect)) {
                orbEffect.add(c);
            } else if (c.name().startsWith("WHEN_") && checkIfEffectHasTiming(orbEffect)) {
                effects.add(orbEffect);
                orbEffect = new ArrayList<>();
                orbEffect.add(c);
            } else {
                orbEffect.add(c);
            }
        }
        if (orbEffect != null) {
            effects.add(orbEffect);
        }
    }

    public boolean checkIfEffectHasTiming(ArrayList<MORECHAOSMOREPOWER.Components> list) {
        return !list.isEmpty() && list.get(0).name().startsWith("WHEN_");
    }

    private String buildID() {
        StringBuilder sbuf = new StringBuilder();
        sbuf.append("DAB ");
        for (MORECHAOSMOREPOWER.Components c : components) {
            sbuf.append(c.name());
            sbuf.append(" ");
        }
        if (sbuf.length() > 0) {
            return sbuf.toString().substring(0, sbuf.length() - 1);
        }
        return sbuf.toString();
    }

    private static String generateName() {
        StringBuilder sbuf = new StringBuilder();
        CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("adjList");
        OrbStrings orbStrings = CardCrawlGame.languagePack.getOrbString("literallyjustorb");
        ArrayList<String> adjs = new ArrayList<>(Arrays.asList(cardStrings.EXTENDED_DESCRIPTION));
        sbuf.append(adjs.get(r.nextInt((adjs.size() - 1 - 0) + 1) + 0));
        sbuf.append(" ");
        sbuf.append(orbStrings.NAME);
        return sbuf.toString();
    }

    private static String generatePassiveDesc() {
        return "";
    }

    private static String generateEvokeDesc() {
        return "";
    }

    @Override
    public void onEvoke() {
        for (ArrayList<MORECHAOSMOREPOWER.Components> e : effects) {
            if (e.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_EVOKE)) {
                activateOrb(e);
            }
        }
    }

    @Override
    public AbstractOrb makeCopy() {
        return new thisentireprojectwasamistake(basePassiveAmount, baseEvokeAmount, components) {
        };
    }

    @Override
    public void onEndOfTurn() {
        for (ArrayList<MORECHAOSMOREPOWER.Components> e : effects) {
            if (e.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_END_OF_TURN)) {
                activateOrb(e);
            }
        }
    }

    @Override
    public void playChannelSFX() {

    }
}
