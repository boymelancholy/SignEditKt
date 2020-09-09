package mcbe.boymelancholy.signeditkt.utils

import cn.nukkit.Player
import itsu.mcbe.form.base.CustomForm
import itsu.mcbe.form.base.Form
import mcbe.boymelancholy.signeditkt.SignEditKt
import mcbe.boymelancholy.signeditkt.forms.*

class FormMaker(controller: Player) {
    private val player: Player = controller

    fun sendForm(number: Int) {
        val form = when(number) {
            call -> (TopForm()).getForm() as Form
            edit -> (EditForm()).getForm(player) as Form
            copy -> (CopyForm()).getForm(player) as Form
            paste -> (PasteForm()).getForm(player) as Form
            clear -> (ClearForm()).getForm(player) as Form
            delete -> (DeleteForm()).getForm(player) as Form
            error -> (CopyExceptionForm()).getForm(player) as Form
            else -> CustomForm()
        }
        SignEditKt.instance.getFormAPI().sendFormToPlayer(player, form)
    }

    companion object {
        const val call: Int = 0
        const val edit: Int = 1
        const val copy: Int = 2
        const val paste: Int = 3
        const val clear: Int = 4
        const val delete: Int = 5
        const val error: Int = 6

        var formCount = 100
    }
}