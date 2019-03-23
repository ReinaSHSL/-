package questionableDecisions.patches;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.CharacterManager;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import questionableDecisions.MORECHAOSMOREPOWER;
import questionableDecisions.characters.PUTMEOUTOFMYMISERY;
import questionableDecisions.util.TextureLoader;

import java.util.ArrayList;
import java.util.Random;

@SpirePatch(
        clz = CharacterSelectScreen.class,
        method = "render"
)
public class CharSelectScreenPatch {
    private static Random r = new Random();
    private static ArrayList<String> characterPortraits = new ArrayList<>();
    private static int rng;
    public static void Prefix(CharacterSelectScreen __instance, SpriteBatch sb) {
        if (characterPortraits.isEmpty()) {
            for (String s : BaseMod.playerPortraitMap.values()) {
                characterPortraits.add(s);
            }
        }
        for (CharacterOption o: __instance.options) {
            if (o.selected && o.c instanceof PUTMEOUTOFMYMISERY) {
               rng = r.nextInt((characterPortraits.size() - 1 + 0) + 1);
                __instance.bgCharImg = TextureLoader.getTexture(characterPortraits.get(rng));
            }
        }

    }
}
