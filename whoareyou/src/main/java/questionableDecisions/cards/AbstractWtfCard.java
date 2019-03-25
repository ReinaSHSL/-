package questionableDecisions.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.unique.DualWieldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.*;
import questionableDecisions.MORECHAOSMOREPOWER;
import questionableDecisions.actions.ChaosTheoryAction;
import questionableDecisions.actions.RerollHandNumbersAction;
import questionableDecisions.characters.PUTMEOUTOFMYMISERY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static com.megacrit.cardcrawl.helpers.CardLibrary.getCard;

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
    private static Random r = new Random();

    public AbstractWtfCard(String id, int cost, CardRarity rarity, int costUpgradeAmount, int damageUpgradeAmount, int blockUpgradeAmount, int magicUpgradeAmount, int wtfMagicUpgradeAmount, int damageAmt, int blockAmt, int magic, int wtfMagic) {
        super(id, generateName(), null, cost, "", CardType.SKILL, PUTMEOUTOFMYMISERY.Enums.REEEEEEE, rarity, CardTarget.NONE);

        this.costUpgradeAmount = costUpgradeAmount;
        this.damageUpgradeAmount = damageUpgradeAmount;
        this.blockUpgradeAmount = blockUpgradeAmount;
        this.magicUpgradeAmount = magicUpgradeAmount;
        this.wtfMagicUpgradeAmount = wtfMagicUpgradeAmount;

        this.baseDamage = damageAmt;
        this.baseBlock = blockAmt;
        this.magicNumber = this.baseMagicNumber = magic;
        this.wtfMagicNumber = this.baseWtfMagicNumber = wtfMagic;

    }

    public String buildID() {
        StringBuilder sbuf = new StringBuilder();
        sbuf.append("DAB ");
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
        CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("adjList");
        UIStrings nounStrings = CardCrawlGame.languagePack.getUIString("nounList");
        ArrayList<String> adjs = new ArrayList<>(Arrays.asList(cardStrings.EXTENDED_DESCRIPTION));
        ArrayList<String> nouns = new ArrayList<>(Arrays.asList(nounStrings.TEXT));
        sbuf.append(adjs.get(r.nextInt((adjs.size() - 1 - 0) + 1) + 0));
        sbuf.append(" ");
        sbuf.append(nouns.get(r.nextInt((nouns.size() - 1 - 0) + 1) + 0));
        return sbuf.toString();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (type == CardType.POWER) {
            act(new ApplyPowerAction(p, p, ))
            return;
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_DAMAGE)) {
            act(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_MULTI_DAMAGE)) {
            for (int i = 0; i < magicNumber; i++) {
                act(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_VULN)) {
            act(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false), magicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_WEAK)) {
            act(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_BLOCK)) {
            act(new GainBlockAction(p, p, block));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_SINGLE_DRAW)) {
            act(new DrawCardAction(p, magicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_MULTI_DRAW)) {
            act(new DrawCardAction(p, magicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_CHAOS)) {
            act(new ChaosTheoryAction());
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_CHAOS_UPGRADED)) {
            for (int i = 0; i < magicNumber; i++) {
                act(new ChaosTheoryAction());
            }
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_EXHAUST_CARD)) {
            act(new ExhaustAction(p, p, magicNumber, false));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_POISON)) {
            act(new ApplyPowerAction(p, p, new PoisonPower(m, p, magicNumber)));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_RETAIN_BLOCK)) {
            act(new ApplyPowerAction(p, p, new BlurPower(p, magicNumber)));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_REROLL_NUMBERS)) {
            act(new RerollHandNumbersAction());
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_FLEX)) {
            act(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
            act(new ApplyPowerAction(p, p, new LoseStrengthPower(p, magicNumber), magicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_SPEED)) {
            act(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber), magicNumber));
            act(new ApplyPowerAction(p, p, new LoseDexterityPower(p, magicNumber), magicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_DUALWIELD)) {
            act(new DualWieldAction(p, magicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_CHAOS_ORBS)) {
            for (int i = 0; i < magicNumber; i++) {
                act(new ChannelAction(AbstractOrb.getRandomOrb(true)));
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
        for (MORECHAOSMOREPOWER.Components c : componentList) {
            if (c.name().startsWith("WHEN_")) {
                type = CardType.POWER;
                target = CardTarget.SELF;
                return;
            }
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_DAMAGE)) {
            type = CardType.ATTACK;
            target = CardTarget.ENEMY;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_MULTI_DAMAGE)) {
            type = CardType.ATTACK;
            target = CardTarget.ENEMY;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_POISON)) {
            type = CardType.SKILL;
            target = CardTarget.ENEMY;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_RETAIN_BLOCK)) {
            type = CardType.SKILL;
            target = CardTarget.ENEMY;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_WEAK) || componentList.contains(MORECHAOSMOREPOWER.Components.WHY_VULN)) {
            type = CardType.SKILL;
            target = CardTarget.ENEMY;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_BLOCK)) {
            type = CardType.SKILL;
            target = CardTarget.SELF;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_SINGLE_DRAW)) {
            type = CardType.SKILL;
            target = CardTarget.SELF;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_MULTI_DRAW)) {
            type = CardType.SKILL;
            target = CardTarget.SELF;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_CHAOS) || componentList.contains(MORECHAOSMOREPOWER.Components.WHY_CHAOS_UPGRADED)) {
            type = CardType.SKILL;
            target = CardTarget.SELF;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_EXHAUST_CARD)) {
            type = CardType.SKILL;
            target = CardTarget.SELF;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_REROLL_NUMBERS)) {
            type = CardType.SKILL;
            target = CardTarget.SELF;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_FLEX) || componentList.contains(MORECHAOSMOREPOWER.Components.WHY_SPEED)) {
            type = CardType.SKILL;
            target = CardTarget.SELF;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_DUALWIELD)) {
            type = CardType.SKILL;
            target = CardTarget.SELF;
        } else if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_CHAOS_ORBS)) {
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

    @Override
    public AbstractCard makeCopy() {
        AbstractWtfCard c = new AbstractWtfCard(this.cardID, cost, rarity, costUpgradeAmount, damageUpgradeAmount, blockUpgradeAmount, magicUpgradeAmount, wtfMagicUpgradeAmount, baseDamage, baseBlock, baseMagicNumber, baseWtfMagicNumber) { };
        c.componentList.addAll(componentList);
        c.setCardInfo();
        c.buildID();
        return c;
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractWtfCard c = new AbstractWtfCard(this.cardID, cost, rarity, costUpgradeAmount, damageUpgradeAmount, blockUpgradeAmount, magicUpgradeAmount, wtfMagicUpgradeAmount, baseDamage, baseBlock, baseMagicNumber, baseWtfMagicNumber) { };
        c.componentList.addAll(componentList);
        c.exhaust = exhaust;
        c.isInnate = isInnate;
        c.isEthereal = isEthereal;
        for (int i = 0; i < timesUpgraded; i++) {
            c.upgrade();
        }
        c.upgraded = upgraded;
        c.freeToPlayOnce = freeToPlayOnce;
        c.misc = misc;
        c.name = name;
        c.costForTurn = costForTurn;
        c.setCardInfo();
        c.buildID();
        return c;
    }
}
