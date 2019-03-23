package questionableDecisions.characters;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.MeteorStrike;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import questionableDecisions.MORECHAOSMOREPOWER;

import java.util.ArrayList;

import static questionableDecisions.MORECHAOSMOREPOWER.*;
import static questionableDecisions.characters.PUTMEOUTOFMYMISERY.Enums.COLOR_GRAY;


public class PUTMEOUTOFMYMISERY extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(MORECHAOSMOREPOWER.class.getName());


    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass AAAAAAAAA;
        @SpireEnum(name = "DEFAULT_GRAY_COLOR")
        public static AbstractCard.CardColor COLOR_GRAY;
        @SpireEnum(name = "DEFAULT_GRAY_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }


    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 9;
    public static final int ORB_SLOTS = 3;


    private static final String ID = makeID("???");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;


    public static final String[] orbTextures = {
            "questionableDecisionsResources/images/char/defaultCharacter/orb/layer1.png",
            "questionableDecisionsResources/images/char/defaultCharacter/orb/layer2.png",
            "questionableDecisionsResources/images/char/defaultCharacter/orb/layer3.png",
            "questionableDecisionsResources/images/char/defaultCharacter/orb/layer4.png",
            "questionableDecisionsResources/images/char/defaultCharacter/orb/layer5.png",
            "questionableDecisionsResources/images/char/defaultCharacter/orb/layer6.png",
            "questionableDecisionsResources/images/char/defaultCharacter/orb/layer1d.png",
            "questionableDecisionsResources/images/char/defaultCharacter/orb/layer2d.png",
            "questionableDecisionsResources/images/char/defaultCharacter/orb/layer3d.png",
            "questionableDecisionsResources/images/char/defaultCharacter/orb/layer4d.png",
            "questionableDecisionsResources/images/char/defaultCharacter/orb/layer5d.png",};


    public PUTMEOUTOFMYMISERY(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "questionableDecisionsResources/images/char/defaultCharacter/orb/vfx.png", null,
                new SpriterAnimation(
                        "questionableDecisionsResources/images/char/defaultCharacter/Spriter/theDefaultAnimation.scml"));


        initializeClass(null,

                THE_DEFAULT_SHOULDER_1,
                THE_DEFAULT_SHOULDER_2,
                THE_DEFAULT_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));


        loadAnimation(
                THE_DEFAULT_SKELETON_ATLAS,
                THE_DEFAULT_SKELETON_JSON,
                1.0f);
        AnimationState.TrackEntry e = state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());


        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 220.0F * Settings.scale);


    }


    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }


    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading starter Deck Strings");

        retVal.add(MeteorStrike.ID);

        return retVal;
    }


    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();


        return retVal;
    }


    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }


    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }


    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }


    @Override
    public AbstractCard.CardColor getCardColor() {
        return COLOR_GRAY;
    }


    @Override
    public Color getCardTrailColor() {
        return MORECHAOSMOREPOWER.DEFAULT_GRAY;
    }


    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }


    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }


    @Override
    public AbstractCard getStartCardForEvent() {
        return new Strike_Red();
    }


    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }


    @Override
    public AbstractPlayer newInstance() {
        return new PUTMEOUTOFMYMISERY(name, chosenClass);
    }


    @Override
    public Color getCardRenderColor() {
        return MORECHAOSMOREPOWER.DEFAULT_GRAY;
    }


    @Override
    public Color getSlashAttackColor() {
        return MORECHAOSMOREPOWER.DEFAULT_GRAY;
    }


    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }


    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }


    @Override
    public String getVampireText() {
        return TEXT[2];
    }

}
