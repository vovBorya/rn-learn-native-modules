import React, {FC, useEffect} from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  useColorScheme,
  Button,
  NativeEventEmitter,
} from 'react-native';

import {Colors} from 'react-native/Libraries/NewAppScreen';

import {CalendarModule, DEFAULT_EVENT_NAME} from './features/native-modules';

const App: FC = () => {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  const onPress = () => {
    CalendarModule.createCalendarEvent(DEFAULT_EVENT_NAME, 'testLocation');
    console.log('We will invoke the native module here!');
  };

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
        backgroundColor={backgroundStyle.backgroundColor}
      />
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={backgroundStyle}>
        <Button
          title="Click to invoke your native module!"
          color="#841584"
          onPress={onPress}
        />
      </ScrollView>
    </SafeAreaView>
  );
};

export default App;
