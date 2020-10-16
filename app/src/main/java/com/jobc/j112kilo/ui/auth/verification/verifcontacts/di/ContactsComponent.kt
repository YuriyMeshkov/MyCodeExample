package com.jobc.j112kilo.ui.auth.verification.verifcontacts.di

import com.jobc.j112kilo.di.AppComponent
import com.jobc.j112kilo.di.ScreenScope
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.ContactsDataFragment
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.SendCodeEmailFragment
import com.jobc.j112kilo.ui.auth.verification.verifcontacts.SendSmsForChangePhone
import dagger.Component

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ContactsViewModelModule::class, ContactsModule::class]
)
interface ContactsComponent {

    fun inject(contactsDataFragment: ContactsDataFragment)
    fun inject(sendCodeEmailFragment: SendCodeEmailFragment)
    fun inject(sendSmsForChangePhone: SendSmsForChangePhone)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent) : Builder
        fun build() : ContactsComponent
    }

    companion object {
        fun init(appComponent: AppComponent) : ContactsComponent {
            return DaggerContactsComponent
                .builder()
                .appComponent(appComponent)
                .build()
        }
    }
}