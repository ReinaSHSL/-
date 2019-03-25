package questionableDecisions;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import questionableDecisions.cards.AbstractWtfCard;
import questionableDecisions.characters.PUTMEOUTOFMYMISERY;
import questionableDecisions.util.IDCheckDontTouchPls;
import questionableDecisions.util.TextureLoader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@SpireInitializer
public class MORECHAOSMOREPOWER implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber {

    public static final Logger logger = LogManager.getLogger(MORECHAOSMOREPOWER.class.getName());

    public enum Components {
        WHY_FIRST_CARD,
        WHY_INNATE,
        WHY_ETHEREAL,
        WHEN_DAMAGE_GIVE,
        WHEN_DAMAGE_GIVE_FINAL,
        WHEN_DAMAGE_FINAL_REC,
        WHEN_DAMAGE_REC,
        WHEN_START_TURN,
        WHEN_DURING_TURN,
        WHEN_POST_DRAW,
        WHEN_END_OF_TURN,
        WHEN_END_OF_ROUND,
        WHEN_AOE,
        WHEN_HEAL,
        WHEN_ATTACKED,
        WHEN_ATTACK,
        WHEN_DO_DMG,
        WHEN_EVOKE,
        WHEN_PLAY_CARD,
        WHEN_USE_CARD,
        WHEN_PLAY_AFTER,
        WHEN_SPEC_TRIG,
        WHEN_DIE,
        WHEN_CHANNEL,
        WHEN_ENERGY_GAIN,
        WHEN_EXHAUST,
        WHEN_MODIFY_BLOCK,
        WHEN_ON_GAIN_BLOCK,
        WHEN_ON_PLAYER_GAIN_BLOCK,
        WHEN_GAIN_CHARGE,
        WHEN_REMOVE,
        WHEN_ENERGY_CHARGE,
        WHEN_DRAW_OR_DISCARD,
        WHEN_AFTER_CARD_PLAY,
        WHEN_INITIAL,
        WHY_BLOCK,
        WHY_SINGLE_DRAW,
        WHY_MULTI_DRAW,
        WHY_CHAOS,
        WHY_CHAOS_UPGRADED,
        POWER_NO_MANA,
        POWER_REDUCE_HAND_SIZE,
        POWER_NO_ENERGY,
        POWER_RESTORE_HANDSIZE,
        POWER_FLASH,
        POWER_REMOVE_THIS_POWER,
        WHY_EXHAUST
    }

    private static String modID;

    private static Random r = new Random();


    private static final String MODNAME = "sksaAS(AFa9sf08asr32ESA";
    private static final String AUTHOR = "r̴̩̬͒̐̾͐̍͝͝ë̴̘̣̱̘̪̄͊͌͗͋͝I̶̫͎̲͕͓̿N̵̝͎͕̥͐̾͗̈́̽͜͜à̵̻̮̣͚̅̾̀";
    private static final String DESCRIPTION = "ŵ̴̧̢̨̡̛̛̟̖͓̘̰̗̜̞̯̦̲͕̹͕͎̦͇̳͚͚͕͖͔͖͙̭̘͍̼͙̭̯͈̘̭̞̯͎͉̘̩͕̱̟̼̮̽̅͌͐̓̏̄̓͋̽̓̉̊͆̀̎͌̌͒͗̋̆̍̽̈́̔̅́̇̾͒̾́̀̍̌͋͋̈͘̚͜͝͝͝͝͝͝͠͝͝͝a̶̡̧̢̛̛͎̯̠̬̖͙̰͓̣̼͔̦͉̖̤̜̳̖͔̱͓̙̲̩͙̯̪̠̯͕̻̫͇̬̳̪͖̺̩̣̪̞͙̼̮̼̖͖̻̲̤̻̟͚͈͉͛̃̎̀̾̓̆̓͂̌̀͋̈̉̀̂̃͐̈́͊̆̏̋̃̔̇́̃̄̐͗͆̅͆̒͐̓̎̉̈́͋̓͋̾͛͑́̏̑͘͘̚̕̕͘̕͠͝͠͝͝K̷̨̨̢̢̡̡̧̧̧̢̨̝̰̱̹̞̹̜͓͍̯̯̟̪̣͎̫͉̟͙̥̠͈͕̲̣̮̹̩̠̲̦̪̹̝̦̗͔̻͍͖̙̖̘͚͍̯͎̺͇̜̗̗̠̠͈̝͔̱̹̲͍͚̦͙̥̳͔͕̩̭͔͛́̓̏̓̃̑̍͋̓͂͘ͅͅê̶̺͖͖̒͛̅̌͊̆̇̋͗͊͒͆̓̄̌̋̂͒̍͗͆̏̒̿̀̈́́̏̅̅͊̊͗̓̎͘̕͝ ̸̢̡̨̢̛͙̻͖̙̤͖̻͈̗̹̘̻̟̜̘̝̍̒̃͑̏̔͗́̿̓͛̒͌̌̇͐̔̑̈́̾̎͋̏͋̈́̐̋͛̇̄̍͋̒̂̄͛̎͐͊̕̕͘͝͝͠ͅU̶̧̨̨̡̨͕͉̺͙͓̳̹͕͔͔̻̜̱̘̩̯̼̰̹̪̻͙̠̰̥͎̘͚̓͐́̋̄͂̅̏̎̏̌̊́͂͆͂̽̓̌̊̈́̕͜͜͠͠͝͝͠Ṗ̸̧̧̧̧̧̢̨̨̨̮̝̞̳͔̟͓͉̩̦̘̭̖̣̩̬̬̻̩͕̜̖͙̹̣̯̞̖̝̙̰̥̳͓̥̩̣͉̒̈͂̀̓̋̃̉̃̆́͂͋́͒̾̔͐̉͊́̄́͑́̇̓̅̿̉͌̓̊́̓̅̈́̒̿̐̀̽̾́̋͊̚͘͜͠͝͠͝͝͝ͅP̷͚̃͗́̂̈́͒͌͂̃͋͌̅̉̓̐̓͐̇̐̄͊̆͋͛̌̎̉̾͘̕͝";


    public static final Color DEFAULT_GRAY = CardHelper.getColor(64.0f, 70.0f, 70.0f);


    public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0f, 53.0f, 18.0f);
    public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0f, 230.0f, 230.0f);
    public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0f, 25.0f, 10.0f);


    private static final String ATTACK_DEFAULT_GRAY = "questionableDecisionsResources/images/512/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY = "questionableDecisionsResources/images/512/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY = "questionableDecisionsResources/images/512/bg_power_default_gray.png";

    private static final String ENERGY_ORB_DEFAULT_GRAY = "questionableDecisionsResources/images/512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "questionableDecisionsResources/images/512/card_small_orb.png";

    private static final String ATTACK_DEFAULT_GRAY_PORTRAIT = "questionableDecisionsResources/images/1024/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY_PORTRAIT = "questionableDecisionsResources/images/1024/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY_PORTRAIT = "questionableDecisionsResources/images/1024/bg_power_default_gray.png";
    private static final String ENERGY_ORB_DEFAULT_GRAY_PORTRAIT = "questionableDecisionsResources/images/1024/card_default_gray_orb.png";


    private static final String THE_DEFAULT_BUTTON = "questionableDecisionsResources/images/charSelect/DefaultCharacterButton.png";
    private static final String THE_DEFAULT_PORTRAIT = "questionableDecisionsResources/images/charSelect/DefaultCharacterPortraitBG.png";
    public static final String THE_DEFAULT_SHOULDER_1 = "questionableDecisionsResources/images/char/defaultCharacter/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "questionableDecisionsResources/images/char/defaultCharacter/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "questionableDecisionsResources/images/char/defaultCharacter/corpse.png";


    public static final String BADGE_IMAGE = "questionableDecisionsResources/images/Badge.png";


    public static final String THE_DEFAULT_SKELETON_ATLAS = "questionableDecisionsResources/images/char/defaultCharacter/skeleton.atlas";
    public static final String THE_DEFAULT_SKELETON_JSON = "questionableDecisionsResources/images/char/defaultCharacter/skeleton.json";



    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/orbs/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }


    public MORECHAOSMOREPOWER() {
        logger.info("Subscribe to BaseMod hooks");

        BaseMod.subscribe(this);


        setModID("questionableDecisions");


        logger.info("Done subscribing");

        logger.info("Creating the color " + PUTMEOUTOFMYMISERY.Enums.REEEEEEE.toString());

        BaseMod.addColor(PUTMEOUTOFMYMISERY.Enums.REEEEEEE, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY,
                DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY,
                ATTACK_DEFAULT_GRAY, SKILL_DEFAULT_GRAY, POWER_DEFAULT_GRAY, ENERGY_ORB_DEFAULT_GRAY,
                ATTACK_DEFAULT_GRAY_PORTRAIT, SKILL_DEFAULT_GRAY_PORTRAIT, POWER_DEFAULT_GRAY_PORTRAIT,
                ENERGY_ORB_DEFAULT_GRAY_PORTRAIT, CARD_ENERGY_ORB);

        logger.info("Done creating the color");
    }


    public static void setModID(String ID) {
        Gson coolG = new Gson();

        InputStream in = MORECHAOSMOREPOWER.class.getResourceAsStream("/IDCheckStrings.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);

        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) {
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION);
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) {
            modID = EXCEPTION_STRINGS.DEFAULTID;
        } else {
            modID = ID;
        }
    }

    public static String getModID() {
        return modID;
    }

    private static void pathCheck() {
        Gson coolG = new Gson();

        InputStream in = MORECHAOSMOREPOWER.class.getResourceAsStream("/IDCheckStrings.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);

        String packageName = MORECHAOSMOREPOWER.class.getPackage().getName();
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources");
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) {
            if (!packageName.equals(getModID())) {
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID());
            }
            if (!resourcePathExists.exists()) {
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources");
            }
        }
    }


    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Default Mod. Hi. =========================");
        MORECHAOSMOREPOWER defaultmod = new MORECHAOSMOREPOWER();
        logger.info("========================= /Default Mod Initialized. Hello World./ =========================");
    }


    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + PUTMEOUTOFMYMISERY.Enums.AAAAAAAAA.toString());

        BaseMod.addCharacter(new PUTMEOUTOFMYMISERY("kdstkl;sd", PUTMEOUTOFMYMISERY.Enums.AAAAAAAAA),
                THE_DEFAULT_BUTTON, THE_DEFAULT_PORTRAIT, PUTMEOUTOFMYMISERY.Enums.AAAAAAAAA);

        receiveEditPotions();
        logger.info("Added " + PUTMEOUTOFMYMISERY.Enums.AAAAAAAAA.toString());
    }


    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");

        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);


        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addUIElement(new ModLabel("MORECHAOSMOREPOWER doesn't have any settings! An example of those may come later.", 400.0f, 700.0f,
                settingsPanel, (me) -> {
        }));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);


        logger.info("Done loading badge Image and mod options");

    }


    public void receiveEditPotions() {
    }


    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");
        logger.info("Done adding relics!");
    }


    @Override
    public void receiveEditCards() {
        for (int i = 0; i < 500; i++) {
            AbstractCard c = generateCardStartOfGame();
            c.rarity = AbstractCard.CardRarity.COMMON;
            BaseMod.addCard(c);
        }
        for (int i = 0; i < 500; i++) {
            AbstractCard c = generateCardStartOfGame();
            c.rarity = AbstractCard.CardRarity.UNCOMMON;
            BaseMod.addCard(c);
        }
        for (int i = 0; i < 500; i++) {
            AbstractCard c = generateCardStartOfGame();
            c.rarity = AbstractCard.CardRarity.RARE;
            BaseMod.addCard(c);
        }
    }


    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings");


        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/cards.json");


        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/power.json");


        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/relic.json");


        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/eng/char.json");


        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/localization/eng/orb.json");


        BaseMod.loadCustomStringsFile(UIStrings.class,
                getModID() + "Resources/localization/eng/ui.json");

        logger.info("Done edittting strings");
    }


    @Override
    public void receiveEditKeywords() {

    }


    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    public static AbstractCard generateCard() {
        ArrayList<Components> allPossibleCardDesc = new ArrayList<>();
        for (MORECHAOSMOREPOWER.Components cd : MORECHAOSMOREPOWER.Components.values()) {
            if (!cd.name().startsWith("WHEN_") && (!cd.name().startsWith("POWER_"))) {
                allPossibleCardDesc.add(cd);
            }
        }
        Collections.shuffle(allPossibleCardDesc);
        int weight = 80;
        int loseWeight = 40;

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
                    weight = 2;
                }
            }
        }
        c.setCardInfo();
        c.resetAttributes();
        c.magicNumber = c.baseMagicNumber;
        c.wtfMagicNumber = c.baseWtfMagicNumber;
        c.cardID = c.buildID();
        Collections.sort(c.componentList);
        return c;
    }

    public static AbstractCard generateCardStartOfGame() {
        ArrayList<Components> allPossibleCardDesc = new ArrayList<>();
        for (MORECHAOSMOREPOWER.Components cd : MORECHAOSMOREPOWER.Components.values()) {
            if (!cd.name().startsWith("WHEN_") && (!cd.name().startsWith("POWER_"))) {
                allPossibleCardDesc.add(cd);
            }
        }
        Collections.shuffle(allPossibleCardDesc);
        int weight = 80;
        int loseWeight = 40;

        int cost = r.nextInt((3) + 1);
        int d = getDamageStart(cost);
        int b = getBlockStart(cost);
        int magic = getMagicStart(cost);
        int wtfMagic = getMagicStart(cost);
        int du = r.nextInt((5 - 3) + 1) + 3;
        int bu = r.nextInt((5 - 3) + 1) + 3;
        int cu = r.nextInt((cost - 0) + 1) + 0;
        int mu = r.nextInt((1 - 0) + 1) + 0;
        int muu = r.nextInt((1 - 0) + 1) + 0;

        AbstractWtfCard c = new AbstractWtfCard("",cost, AbstractCard.CardRarity.SPECIAL, cu, du, bu, mu, muu, d, b, magic, wtfMagic) {
        };

        for (MORECHAOSMOREPOWER.Components de : allPossibleCardDesc) {
            int rng = r.nextInt((99 - 0) + 1) + 0;
            if (rng < weight) {
                c.componentList.add(de);
                weight -= loseWeight;
                if (weight <= 0) {
                    weight = 2;
                }
            }
        }
        c.setCardInfo();
        c.resetAttributes();
        c.magicNumber = c.baseMagicNumber;
        c.wtfMagicNumber = c.baseWtfMagicNumber;
        c.cardID = c.buildID();
        Collections.sort(c.componentList);
        return c;
    }

    private static int getDamage(int cost) {
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

    private static int getBlock(int cost) {
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

    private static int getMagic(int cost) {
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

    private static int getDamageStart(int cost) {
        switch (cost) {
            case 0:
                return r.nextInt((5 - 3) + 1) + 3;
            case 1:
                return r.nextInt((12 - 9) + 1) + 9;
            case 2:
                return r.nextInt((24 - 17) + 1) + 17;
            case 3:
                return r.nextInt((36 - 26) + 1) + 26;
        }
        return 0;
    }

    private static int getBlockStart(int cost) {
        switch (cost) {
            case 0:
                return r.nextInt((5 - 3) + 1) + 3;
            case 1:
                return r.nextInt((9 - 7) + 1) + 7;
            case 2:
                return r.nextInt((20 - 14) + 1) + 14;
            case 3:
                return r.nextInt((34 - 26) + 1) + 26;
        }
        return 0;
    }

    private static int getMagicStart(int cost) {
        switch (cost) {
            case 0:
                return r.nextInt((1 - 1) + 1) + 1;
            case 1:
                return r.nextInt((2 - 1) + 1) + 1;
            case 2:
                return r.nextInt((3 - 2) + 1) + 2;
            case 3:
                return r.nextInt((5 - 3) + 1) + 3;
        }
        return 0;
    }

}
