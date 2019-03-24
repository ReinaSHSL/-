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
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import questionableDecisions.characters.PUTMEOUTOFMYMISERY;
import questionableDecisions.util.IDCheckDontTouchPls;
import questionableDecisions.util.TextureLoader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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
        WHY_AOE_DAMAGE_X,
        WHY_BLOCK,
        WHY_SINGLE_DRAW,
        WHY_MULTI_DRAW,
        WHY_CHAOS,
        WHY_CHAOS_UPGRADED,
        WHY_NO_MANA,
        WHY_REDUCE_HAND_SIZE,
        WHY_NO_ENERGY,
        WHY_RESTORE_HANDSIZE,
        WHY_FLASH,
        WHY_REMOVE_THIS_POWER,
        WHY_EXHAUST
    }

    private static String modID;


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

        logger.info("Creating the color " + PUTMEOUTOFMYMISERY.Enums.COLOR_GRAY.toString());

        BaseMod.addColor(PUTMEOUTOFMYMISERY.Enums.COLOR_GRAY, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY,
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

        logger.info("Done edittting strings");
    }


    @Override
    public void receiveEditKeywords() {

    }


    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

}
