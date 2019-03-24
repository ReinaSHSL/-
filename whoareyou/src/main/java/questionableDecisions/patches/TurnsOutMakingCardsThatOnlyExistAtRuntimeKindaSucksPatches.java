package questionableDecisions.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import questionableDecisions.cards.AbstractWtfCard;

public class TurnsOutMakingCardsThatOnlyExistAtRuntimeKindaSucksPatches {

    @SpirePatch(
            clz = CardLibrary.class,
            method = "getCopy",
            paramtypez = {
                    String.class,
                    String.class,
                    int.class
            }
    )
    public static class GetCopyAndUpgradePatch {
        public static SpireReturn Prefix(String id, int upgrades, int misc) {
            AbstractCard cardToFind = CardLibrary.cards.get(id);
            if (cardToFind == null) {
                AbstractWtfCard.makeCopy(id);
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = CardLibrary.class,
            method = "getCopy",
            paramtypez = {
                    String.class,
            }
    )
    public static class GetCopyAndUpgradePatchSingleParam {
        public static SpireReturn Prefix(String id) {
            AbstractCard cardToFind = CardLibrary.cards.get(id);
            if (cardToFind == null) {
                AbstractWtfCard.makeCopy(id);
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }
}
