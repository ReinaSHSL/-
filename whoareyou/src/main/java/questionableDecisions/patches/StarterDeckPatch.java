package questionableDecisions.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import questionableDecisions.MORECHAOSMOREPOWER;
import questionableDecisions.characters.PUTMEOUTOFMYMISERY;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "initializeStarterDeck"
)
public class StarterDeckPatch {
    public static SpireReturn Prefix(AbstractPlayer __instance) {
        if (__instance instanceof PUTMEOUTOFMYMISERY) {
            for (int i = 0; i < 10; i++) {
                __instance.masterDeck.addToTop(MORECHAOSMOREPOWER.generateCard());
            }
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
}
