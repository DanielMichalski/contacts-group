package pl.dmichalski.contacts.utils;

public class Const {

    public static interface TitleFramesAndDlgs {
        String CONTACT_FRAME = "Zarządzanie kontaktami";
    }

    public static interface ButtonLbls {
        String BTN_SAVE = "Zapisz";
        String BTN_CANCEL = "Wyczyść";
        String BTN_DELETE = "Usuń";
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
    }

    public static interface TableColumns {
        String NAME_AND_SURNAME = "Imię i nazwisko";
        String PHONE_NUMBER = "Numer telefonu";
        String TYPE = "Typ";
        String ADDRESS = "Adres";
    }
}