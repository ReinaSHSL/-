package questionableDecisions.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import questionableDecisions.MORECHAOSMOREPOWER;
import questionableDecisions.actions.ChaosTheoryAction;
import questionableDecisions.characters.PUTMEOUTOFMYMISERY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static questionableDecisions.MORECHAOSMOREPOWER.makeID;

public abstract class AbstractWtfCard extends CustomCard {
    public int wtfMagicNumber;
    public int baseWtfMagicNumber;
    public boolean wtfMagicNumberChanged = false;
    public ArrayList<MORECHAOSMOREPOWER.Components> componentList= new ArrayList<>();
    public int damageUpgradeAmount;
    public int blockUpgradeAmount;
    public int magicUpgradeAmount;
    public int costUpgradeAmount;
    public int wtfMagicUpgradeAmount;
    private PowerStrings powerStrings;

    public AbstractWtfCard(String id, int cost, CardRarity rarity, int costUpgradeAmount, int damageUpgradeAmount, int blockUpgradeAmount, int magicUpgradeAmount, int wtfMagicUpgradeAmount, int damageAmt, int blockAmt, int magic, int wtfMagic) {
        super(id, generateName(), null, cost, "", CardType.CURSE, PUTMEOUTOFMYMISERY.Enums.COLOR_GRAY, rarity, CardTarget.NONE);

        this.costUpgradeAmount = costUpgradeAmount;
        this.damageUpgradeAmount = damageUpgradeAmount;
        this.blockUpgradeAmount = blockUpgradeAmount;
        this.magicUpgradeAmount = magicUpgradeAmount;
        this.wtfMagicUpgradeAmount = wtfMagicUpgradeAmount;

        this.baseDamage = damageAmt;
        this.baseBlock = blockAmt;
        this.magicNumber = this.baseMagicNumber = magic;
        this.wtfMagicNumber = this.baseWtfMagicNumber = wtfMagic;

        Collections.sort(componentList);

        setMisc();
    }

    public String buildID() {
        StringBuilder sbuf = new StringBuilder();
        for (MORECHAOSMOREPOWER.Components c : componentList) {
            sbuf.append(c.name());
            sbuf.append(" ");
        }
        if (sbuf.length() > 0) {
            return sbuf.toString().substring(0, sbuf.length() - 1);
        }
        return sbuf.toString();
    }

    public static String generateName() {
        StringBuilder sbuf = new StringBuilder();
        return sbuf.toString();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_BLOCK)) {
            act(new GainBlockAction(p, p, block));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_SINGLE_DRAW)) {
            act(new DrawCardAction(p, baseMagicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_MULTI_DRAW)) {
            act(new DrawCardAction(p, baseMagicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_CHAOS)) {
            act(new ChaosTheoryAction());
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_CHAOS_UPGRADED)) {
            for (int i = 0; i < magicNumber; i++) {
                act(new ChaosTheoryAction());
            }
        }
    }

    public void setCardInfo() {
        findCardAttributes();
        if (this.type == CardType.POWER) {
            generatePowerComponentList();
        } else {
            generateComponentList();
        }
    }

    public void setCardTags(MORECHAOSMOREPOWER.Components... cd) {
        componentList.addAll(Arrays.asList(cd));
        setCardInfo();
    }

    //ENEMY AOE TAKES PRIORITY OVER EVERYTHING
    //ATTACK OVER POWER OVER SKILL
    public void findCardAttributes() {
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_EXHAUST)) {
            exhaust = true;
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_ETHEREAL)) {
            isEthereal = true;
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_INNATE)) {
            isInnate = true;
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_AOE_DAMAGE_X)) {
            type = CardType.ATTACK;
            target = CardTarget.ALL_ENEMY;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_BLOCK)) {
            type = CardType.SKILL;
            target = CardTarget.SELF;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_SINGLE_DRAW)) {
            type = CardType.SKILL;
            target = CardTarget.SELF;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_MULTI_DRAW)) {
            type = CardType.SKILL;
            target = CardTarget.SELF;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_CHAOS)) {
            type = CardType.SKILL;
            target = CardTarget.SELF;
        }
    }

    public void generateComponentList() {
        StringBuilder sbuf = new StringBuilder();
        CardStrings cardStrings;
        for (MORECHAOSMOREPOWER.Components d : componentList) {
            cardStrings = CardCrawlGame.languagePack.getCardStrings(d.name());
            sbuf.append(cardStrings.DESCRIPTION);
            sbuf.append(" NL ");
        }
        if (sbuf.length() >= 4) {
            rawDescription = sbuf.toString().substring(0, sbuf.length() - 4);
            initializeDescription();
        }
    }

    public void generatePowerComponentList() {
        StringBuilder sbuf = new StringBuilder();
        CardStrings cardStrings;
        for (MORECHAOSMOREPOWER.Components d : componentList) {
            cardStrings = CardCrawlGame.languagePack.getCardStrings(d.name());
            sbuf.append(cardStrings.DESCRIPTION);
            if (!componentList.toString().startsWith("WHEN_")) {
                sbuf.append(" NL ");
            }
        }
        if (sbuf.length() >= 4) {
            rawDescription = sbuf.toString().substring(0, sbuf.length() - 4);
            initializeDescription();
        }
    }

    public void upgradeWtfMagicNumber(int i) {
        baseWtfMagicNumber += i;
        wtfMagicNumberChanged = true;
    }


    @Override
    public boolean hasEnoughEnergy() {
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_FIRST_CARD)) {
            return super.hasEnoughEnergy() && AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty();
        } else {
            return super.hasEnoughEnergy();
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            if (damageUpgradeAmount != 0) upgradeDamage(damageUpgradeAmount);
            if (blockUpgradeAmount != 0) upgradeBlock(blockUpgradeAmount);
            if (costUpgradeAmount != cost) upgradeBaseCost(costUpgradeAmount);
            if (magicUpgradeAmount != 0) upgradeMagicNumber(magicUpgradeAmount);
            if (wtfMagicUpgradeAmount != 0) upgradeWtfMagicNumber(wtfMagicUpgradeAmount);
        }
    }

    void act (AbstractGameAction a) { AbstractDungeon.actionManager.addToBottom(a); }

    void actt (AbstractGameAction a) { AbstractDungeon.actionManager.addToTop(a); }

    public void setMisc() {
        String s = baseDamage + " " + baseBlock + " " + baseMagicNumber + " " + baseWtfMagicNumber + " " + cost;
        misc = Integer.valueOf(s);
    }

    public static AbstractCard makeCopy(String s) {

    }
}
