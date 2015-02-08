package pl.dmichalski.contacts.utils;

public class Const {

    public static interface TitleFramesAndDlgs {
        String CONTACT_REGISTRATION_FRAME = "Zarządzanie kontaktami";
        String SHOW_CONTACT_FRAME = "Kontakty";
    }

    public static interface ButtonLbls {
        String BTN_SAVE = "Zapisz";
        String BTN_CANCEL = "Wyczyść";
        String BTN_DELETE = "Usuń";
        String BTN_CONTACTS = "Zarządzanie kontaktami";
        String BTN_GROUP_ADD = "Dodaj grupę";
        String ALL_CONTACTS = "Wszystkie";
        String PRIVATE_CONTACTS = "Prywatne";
        String BUSINESS_CONTACTS = "Biznesowe";
    }

    public static interface Labels {
        String NAME = "Imię:";
        String SURNAME = "Nazwisko:";
        String CLIENT_TYPE = "Typ klienta:";
        String PHONE_NUMBER = "Numer telefonu:";
        String ADDRESS = "Adres:";
        String CONTACT_FORM = "Rejestracja kontaktów";
        String PRIVATE = "Prywatny";
        String BUSINESS = "Biznesowy";
        String NAME_AND_SURNAME = "Imię i nazwisko";
        String FIND = "Szukaj";
        String CONTACTS = "Kontakty";
        String DEFAULT_CONTACT_GROUP = "Bez grupy";
        String GROUP = "Nazwa grupy";
    }

    public static interface Menu {
        String APPLICATION_MENU = "Aplikacja";
    }

    public static interface MenuItem {
        String CLOSE_MENU_ITEM = "Zamknij";
    }

    public static interface Strings {
        String NAME_REQUIRED = "Musisz podać imię i nazwisko żeby móc wyszukać kontakt";
        String ROW_DELETE_REQUIRED = "Musisz wybrać wiersz który chcesz usunąć";
        String INFORMATION = "Informacja";
        String ERROR = "Błąd";
        String FIELDS_REQUIRED = "Wypełnij wszystkie dane";
        String FILE_WRITE_ERROR = "Wystąpił błąd podczas zapisu pliku ";
        String FILE_OPEN_ERROR = "Wystąpił błąd podczas odczytu pliku ";
        String READ_CONFIRMATION = "Czy chcesz wczytać plik z kontaktami?";
        String SAVE_CONFIRMATION = "Czy chcesz zapisać zmiany?";
        String GROUP_EXIST = "Grupa o podanej nazwie już istnieje";
        String GROUP_NAME_REQUIRED = "Podaj nazwe grupy";
    }

    public static interface TableColumns {
        String NAME_AND_SURNAME = "Imię i nazwisko";
        String PHONE_NUMBER = "Numer telefonu";
        String TYPE = "Typ";
        String ADDRESS = "Adres / Nazwa firmy";
        String GROUP_NAME = "Nazwa grupy";
    }
}