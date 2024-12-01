package net.micg.lab5.di

import dagger.Binds
import dagger.Module
import net.micg.lab5.data.LampRepository
import net.micg.lab5.data.LampRepositoryImpl
import net.micg.lab5.domain.GetBrightnessLevelsUseCase
import net.micg.lab5.domain.GetBrightnessLevelsUseCaseImpl
import net.micg.lab5.domain.GetColorNamesUseCase
import net.micg.lab5.domain.GetColorNamesUseCaseImpl
import net.micg.lab5.domain.GetColorsUseCase
import net.micg.lab5.domain.GetColorsUseCaseImpl
import net.micg.lab5.domain.GetCurrentBrightnessUseCase
import net.micg.lab5.domain.GetCurrentBrightnessUseCaseImpl
import net.micg.lab5.domain.GetCurrentColorUseCase
import net.micg.lab5.domain.GetCurrentColorUseCaseImpl
import net.micg.lab5.domain.GetLampStateUseCase
import net.micg.lab5.domain.GetLampStateUseCaseImpl
import net.micg.lab5.domain.SetBrightnessUseCase
import net.micg.lab5.domain.SetBrightnessUseCaseImpl
import net.micg.lab5.domain.SetColorUseCase
import net.micg.lab5.domain.SetColorUseCaseImpl
import net.micg.lab5.domain.TurnOffLampUseCase
import net.micg.lab5.domain.TurnOffLampUseCaseImpl
import net.micg.lab5.domain.TurnOnLampUseCase
import net.micg.lab5.domain.TurnOnLampUseCaseImpl

@Module
interface AppBindsModule {
    @Binds
    @AppComponentScope
    fun bindLampRepository(repository: LampRepositoryImpl): LampRepository

    @Binds
    @AppComponentScope
    fun bindTurnOnLampUseCase(useCase: TurnOnLampUseCaseImpl): TurnOnLampUseCase

    @Binds
    @AppComponentScope
    fun bindTurnOffLampUseCase(useCase: TurnOffLampUseCaseImpl): TurnOffLampUseCase

    @Binds
    @AppComponentScope
    fun bindGetLampStateUseCase(useCase: GetLampStateUseCaseImpl): GetLampStateUseCase

    @Binds
    @AppComponentScope
    fun bindGetColorsUseCase(useCase: GetColorsUseCaseImpl): GetColorsUseCase

    @Binds
    @AppComponentScope
    fun bindSetColorUseCase(useCase: SetColorUseCaseImpl): SetColorUseCase

    @Binds
    @AppComponentScope
    fun bindGetColorNamesUseCase(useCase: GetColorNamesUseCaseImpl): GetColorNamesUseCase

    @Binds
    @AppComponentScope
    fun bindGetCurrentColorUseCase(useCase: GetCurrentColorUseCaseImpl): GetCurrentColorUseCase

    @Binds
    @AppComponentScope
    fun bindGetBrightnessLevelsUseCase(useCase: GetBrightnessLevelsUseCaseImpl): GetBrightnessLevelsUseCase

    @Binds
    @AppComponentScope
    fun bindSetBrightnessUseCase(useCase: SetBrightnessUseCaseImpl): SetBrightnessUseCase

    @Binds
    @AppComponentScope
    fun bindGetCurrentBrightnessUseCase(useCase: GetCurrentBrightnessUseCaseImpl): GetCurrentBrightnessUseCase
}
