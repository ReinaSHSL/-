package questionableDecisions.powers;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.unique.DualWieldAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.*;
import questionableDecisions.MORECHAOSMOREPOWER;
import questionableDecisions.actions.ChaosTheoryAction;
import questionableDecisions.actions.DoublePowerAction;
import questionableDecisions.actions.RerollHandNumbersAction;
import questionableDecisions.actions.ScaleAction;

import java.util.ArrayList;

public abstract class godpleaseendme extends AbstractPower {
    public ArrayList<MORECHAOSMOREPOWER.Components> components;
    private boolean reduceEachTurn;
    private PowerStrings powerStrings;
    ArrayList<ArrayList<MORECHAOSMOREPOWER.Components>> effects = new ArrayList<>();
    private AbstractPlayer p = AbstractDungeon.player;
    private AbstractCreature m = AbstractDungeon.getRandomMonster();
    private int damage;
    private int magicNumber;
    private int block;

    public godpleaseendme(String ID, String name, AbstractCreature owner, int amount, ArrayList<MORECHAOSMOREPOWER.Components> components, boolean reduceEachTurn) {
        this.owner = owner;
        this.amount = amount;
        this.name = name;
        this.ID = ID;
        this.components = components;
        this.reduceEachTurn = reduceEachTurn;
        this.damage = amount;
        this.block = amount;
        this.magicNumber = amount;
        buildEffects(this.components);
    }

    private void triggerPower(ArrayList<MORECHAOSMOREPOWER.Components> componentList) {
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_DAMAGE)) {
            flash();
            act(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_MULTI_DAMAGE)) {
            flash();
            for (int i = 0; i < magicNumber; i++) {
                act(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_VULN)) {
            flash();
            act(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false), magicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_WEAK)) {
            flash();
            act(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_BLOCK)) {
            flash();
            act(new GainBlockAction(p, p, block));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_SINGLE_DRAW)) {
            flash();
            act(new DrawCardAction(p, magicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_MULTI_DRAW)) {
            flash();
            act(new DrawCardAction(p, magicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_CHAOS)) {
            flash();
            act(new ChaosTheoryAction());
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_CHAOS_UPGRADED)) {
            flash();
            for (int i = 0; i < magicNumber; i++) {
                flash();
                act(new ChaosTheoryAction());
            }
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_EXHAUST_CARD)) {
            flash();
            act(new ExhaustAction(p, p, magicNumber, false));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_POISON)) {
            flash();
            act(new ApplyPowerAction(p, p, new PoisonPower(m, p, magicNumber)));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_RETAIN_BLOCK)) {
            flash();
            act(new ApplyPowerAction(p, p, new BlurPower(p, magicNumber)));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_REROLL_NUMBERS)) {
            flash();
            act(new RerollHandNumbersAction());
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_FLEX)) {
            flash();
            act(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
            act(new ApplyPowerAction(p, p, new LoseStrengthPower(p, magicNumber), magicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_SPEED)) {
            flash();
            act(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber), magicNumber));
            act(new ApplyPowerAction(p, p, new LoseDexterityPower(p, magicNumber), magicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_DUALWIELD)) {
            flash();
            act(new DualWieldAction(p, magicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_CHAOS_ORBS)) {
            flash();
            for (int i = 0; i < magicNumber; i++) {
                flash();
                act(new ChannelAction(AbstractOrb.getRandomOrb(true)));
            }
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_HAVOC)) {
            flash();
            act(new PlayTopCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), true));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_THORNS)) {
            flash();
            act(new ApplyPowerAction(p, p, new ThornsPower(p, magicNumber), magicNumber));
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_DOUBLE_POWER)) {
            flash();
            act(new DoublePowerAction());
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_SCALE)) {
            flash();
            amount += amount;
        }
        if (componentList.contains(MORECHAOSMOREPOWER.Components.WHY_RETAIN)) {
            flash();
            act(new RetainCardsAction(p, magicNumber));
        }
    }

    public void buildEffects(ArrayList<MORECHAOSMOREPOWER.Components> list) {
        System.out.println(components);
        ArrayList<MORECHAOSMOREPOWER.Components> powerEffect = new ArrayList<>();
        for (MORECHAOSMOREPOWER.Components cd : list) {
            if (cd.name().startsWith("WHEN_") && !checkIfEffectHasTiming(powerEffect)) {
                powerEffect.add(cd);
            } else if (cd.name().startsWith("WHEN_") && checkIfEffectHasTiming(powerEffect)) {
                effects.add(powerEffect);
                powerEffect = new ArrayList<>();
                powerEffect.add(cd);
            } else {
                powerEffect.add(cd);
            }
        }
        if (powerEffect != null) {
            effects.add(powerEffect);
        }
    }

    public boolean checkIfEffectHasTiming(ArrayList<MORECHAOSMOREPOWER.Components> list) {
        return !list.isEmpty() && list.get(0).name().startsWith("WHEN_");
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_DAMAGE_GIVE)) {
                triggerPower(cdList);
                return damage;
            }
        }
        return damage;
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_DAMAGE_GIVE_FINAL)) {
                triggerPower(cdList);
                return damage;
            }
        }
        return damage;
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_DAMAGE_FINAL_REC)) {
                triggerPower(cdList);
                return damage;
            }
        }
        return damage;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_DAMAGE_REC)) {
                triggerPower(cdList);
                return damage;
            }
        }
        return damage;
    }

    @Override
    public void atStartOfTurn() {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_START_TURN)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_POST_DRAW)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_END_OF_TURN)) {
                triggerPower(cdList);
            }
        }
        if (reduceEachTurn) {
            act(new ReducePowerAction(owner, owner, this, 1));
        }
    }

    @Override
    public void atEndOfRound() {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_END_OF_ROUND)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public int onHeal(int healAmount) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_HEAL)) {
                triggerPower(cdList);
                return healAmount;
            }
        }
        return healAmount;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_ATTACKED)) {
                triggerPower(cdList);
                return damageAmount;
            }
        }
        return damageAmount;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_ATTACK)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public void onInflictDamage(DamageInfo info, int damageAmount, AbstractCreature target) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_DO_DMG)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public void onEvokeOrb(AbstractOrb orb) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_EVOKE)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_PLAY_CARD)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_USE_CARD)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_PLAY_AFTER)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public void onChannel(AbstractOrb orb) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_CHANNEL)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public void atEnergyGain() {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_ENERGY_GAIN)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public void onExhaust(AbstractCard card) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_EXHAUST)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public void onGainedBlock(float blockAmount) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_ON_GAIN_BLOCK)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_ON_PLAYER_GAIN_BLOCK)) {
                triggerPower(cdList);
            }
        }
        return MathUtils.floor(blockAmount);
    }

    @Override
    public void onGainCharge(int chargeAmount) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_GAIN_CHARGE)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public void onEnergyRecharge() {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_ENERGY_CHARGE)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public void onDrawOrDiscard() {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_DRAW_OR_DISCARD)) {
                triggerPower(cdList);
            }
        }
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        for (ArrayList<MORECHAOSMOREPOWER.Components> cdList : effects) {
            if (cdList.get(0).equals(MORECHAOSMOREPOWER.Components.WHEN_AFTER_CARD_PLAY)) {
                triggerPower(cdList);
            }
        }
    }


    void act(AbstractGameAction a) {
        AbstractDungeon.actionManager.addToBottom(a);
    }

    void actt(AbstractGameAction a) {
        AbstractDungeon.actionManager.addToTop(a);
    }

}
